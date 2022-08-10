package com.d209.mungtopia.controller;

import com.google.gson.JsonObject;
import io.openvidu.java.client.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/meeting")
public class MeetingController {

    private OpenVidu openVidu;
    // DB 대체
    private Map<String, Session> mapSessions = new ConcurrentHashMap<>();
    private Map<String, Map<String, OpenViduRole>> mapSessionNamesTokens = new ConcurrentHashMap<>();

    // 포트 확인하기!
    private String OPENVIDU_URL = "https://i7d209.p.ssafy.io:8443";
    private String SECRET = "mung209pro";

    public MeetingController() {
        this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
    }

    @PostMapping("/{user_seq}")
    public ResponseEntity<JSONObject> token(@PathVariable("user_seq") Long userSeq, HttpSession httpSession){
        // 세션 설정 ?
        System.out.println("userSeq = " + httpSession.toString());
        httpSession.setAttribute("loggedUser", userSeq);
        String serverData = "{\"serverData\": \"" + httpSession.getAttribute("loggedUser") + "\"}";
        ConnectionProperties connectionProperties = new ConnectionProperties
                .Builder()
                .type(ConnectionType.WEBRTC)
                .data(serverData)
                .build();

        JSONObject responseJson = new JSONObject();
        // 바로 세션 생성
        try{
            System.out.println(" ========== 세션 생성 직전");
            Session session = this.openVidu.createSession();
            String token = session.createConnection(connectionProperties).getToken();
            String sessionName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss-")) + userSeq;

            // Store the session and the token in our collections
            // table에 저장해야할 것 같음!
            // sessionName : session 이렇게 저장
            this.mapSessions.put(sessionName, session);//
            this.mapSessionNamesTokens.put(sessionName, new ConcurrentHashMap<>());
			/*
			mapSessionNamesTokens -
			{
				token : role
			}
			* */
            this.mapSessionNamesTokens.get(sessionName).put(token, OpenViduRole.PUBLISHER);

            // Prepare the response with the token
            responseJson.put("token", token);

            // Return the response to the client
            return new ResponseEntity<>(responseJson, HttpStatus.OK);
        }catch (Exception e){
            return getErrorResponse(e);
        }
    }

    @RequestMapping(value = "/remove-user", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> removeUser(@RequestBody String sessionNameToken, HttpSession httpSession)
            throws Exception {
        try {
            checkUserLogged(httpSession);
        } catch (Exception e) {
            return getErrorResponse(e);
        }
        System.out.println("Removing user | {sessionName, token}=" + sessionNameToken);

        // Retrieve the params from BODY
        JSONObject sessionNameTokenJSON = (JSONObject) new JSONParser().parse(sessionNameToken);
        String sessionName = (String) sessionNameTokenJSON.get("sessionName");
        String token = (String) sessionNameTokenJSON.get("token");

        // If the session exists
        if (this.mapSessions.get(sessionName) != null && this.mapSessionNamesTokens.get(sessionName) != null) {

            // If the token exists
            if (this.mapSessionNamesTokens.get(sessionName).remove(token) != null) {
                // User left the session
                if (this.mapSessionNamesTokens.get(sessionName).isEmpty()) { // token 삭제
                    // Last user left: session must be removed
                    this.mapSessions.remove(sessionName); // 세션 지우기
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
