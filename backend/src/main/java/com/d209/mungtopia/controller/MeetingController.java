package com.d209.mungtopia.controller;

import com.d209.mungtopia.entity.Application;
import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.repository.InfApplicationRepository;
import com.d209.mungtopia.repository.InfBoardRepository;
import io.openvidu.java.client.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/meeting")
public class MeetingController {

    private final InfApplicationRepository infApplicationRepository;
    private final InfBoardRepository infBoardRepository;

    private OpenVidu openVidu;

    // 세션 저장
    private Map<Long, Session> mapSessions = new ConcurrentHashMap<>();
    // 세션 이름으로 토큰 저장
    private Map<Long, Map<Long, String>> mapSessionNamesTokens = new ConcurrentHashMap<>();

    private String OPENVIDU_URL = "https://i7d209.p.ssafy.io:8443";
    private String SECRET = "mung209pro";

    @Autowired
    public MeetingController(InfApplicationRepository infApplicationRepository, InfBoardRepository infBoardRepository) {
        this.infApplicationRepository = infApplicationRepository;
        this.infBoardRepository = infBoardRepository;
        this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
    }

    @PostMapping("/{user_seq}")
    public ResponseEntity<JSONObject> token(@PathVariable("user_seq") long userSeq, HttpSession httpSession, @RequestBody Long applicationId) {
        // 유저 정보 session에 저장
        httpSession.setAttribute("loggedUser", userSeq);
        String serverData = "{\"serverData\": \"" + httpSession.getAttribute("loggedUser") + "\"}";
        // connection 설정
        ConnectionProperties connectionProperties = new ConnectionProperties
                .Builder()
                .type(ConnectionType.WEBRTC)
                .data(serverData)
                .build();

        // output 설정
        JSONObject responseJson = new JSONObject();

        //만약에 application_id로 sessionName을 조회했을 때 있으면 token update
        if (this.mapSessions.containsKey(applicationId)) { // 세션 존재
            try {
                String token = this.mapSessions.get(applicationId).createConnection(connectionProperties).getToken();

                // token userSeq를 key값으로 저장
                this.mapSessionNamesTokens.get(applicationId).put(userSeq, token);

                responseJson.put("token", token);
                return new ResponseEntity<>(responseJson, HttpStatus.OK);
            } catch (OpenViduJavaClientException e1) {
                return getErrorResponse(e1);
            } catch (OpenViduHttpException e2) {
                if (404 == e2.getStatus()) {
                    this.mapSessions.remove(applicationId);
                    this.mapSessionNamesTokens.remove(applicationId);
                }
            }
        }
        //세션 없음 ->  세션 생성 후 token 발급
        try {
            Session session = this.openVidu.createSession();
            String token = session.createConnection(connectionProperties).getToken();

            this.mapSessions.put(applicationId, session);
            this.mapSessionNamesTokens.put(applicationId, new ConcurrentHashMap<>());
            this.mapSessionNamesTokens.get(applicationId).put(userSeq, token);

            // 토큰 전송
            responseJson.put("token", token);

            return new ResponseEntity<>(responseJson, HttpStatus.OK);
        } catch (Exception e) {
            return getErrorResponse(e);
        }
    }

    @DeleteMapping("/{application_id}")
    public ResponseEntity<JSONObject> removeUser(@PathVariable("application_id") long applicationId, HttpSession httpSession) throws Exception {
        try {
            // 세션이 있는지 확인
            checkUserLogged(httpSession);
        } catch (Exception e) {
            return getErrorResponse(e);
        }

        long userSeq = (long) httpSession.getAttribute("loggedUser");

        // If the session exists
        if (this.mapSessions.get(applicationId) != null && this.mapSessionNamesTokens.get(applicationId) != null) {
            // If the token exists
            if (this.mapSessionNamesTokens.get(applicationId).remove(userSeq) != null) {
                // User left the session
                if (this.mapSessionNamesTokens.get(applicationId).isEmpty()) { // token 삭제
                    // Last user left: session must be removed
                    this.mapSessions.remove(applicationId); // 세션 지우기
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // The TOKEN wasn't valid
                System.out.println("Problems in the app server: the TOKEN wasn't valid");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            // The SESSION does not exist
            System.out.println("Problems in the app server: the SESSION does not exist");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void checkUserLogged(HttpSession httpSession) throws Exception {
        if (httpSession == null || httpSession.getAttribute("loggedUser") == null) {
            throw new Exception("User not logged");
        }
    }

    private ResponseEntity<org.json.simple.JSONObject> getErrorResponse(Exception e) {
        org.json.simple.JSONObject json = new org.json.simple.JSONObject();
        json.put("cause", e.getCause());
        json.put("error", e.getMessage());
        json.put("exception", e.getClass());
        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
