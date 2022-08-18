-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: i7d209.p.ssafy.io    Database: mungtopia
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adoption_process`
--

DROP TABLE IF EXISTS `adoption_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adoption_process` (
  `adoption_process_id` bigint NOT NULL AUTO_INCREMENT,
  `step` int DEFAULT NULL,
  `step_status` tinyint DEFAULT NULL,
  `application_id` bigint DEFAULT NULL,
  PRIMARY KEY (`adoption_process_id`),
  KEY `fk_adoption_process_application1_idx` (`application_id`),
  CONSTRAINT `fk_adoption_process_application1` FOREIGN KEY (`application_id`) REFERENCES `application` (`application_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adoption_process`
--

LOCK TABLES `adoption_process` WRITE;
/*!40000 ALTER TABLE `adoption_process` DISABLE KEYS */;
INSERT INTO `adoption_process` VALUES (38,2,0,53),(39,6,0,54),(40,1,0,55),(41,2,0,56),(42,2,0,57),(44,1,0,59),(46,1,0,61);
/*!40000 ALTER TABLE `adoption_process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adoption_step_date`
--

DROP TABLE IF EXISTS `adoption_step_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adoption_step_date` (
  `adoption_step_date_id` bigint NOT NULL AUTO_INCREMENT,
  `step` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `adoption_process_id` bigint DEFAULT NULL,
  PRIMARY KEY (`adoption_step_date_id`),
  KEY `fk_adoption_step_date_adoption_process1_idx` (`adoption_process_id`),
  CONSTRAINT `fk_adoption_step_date_adoption_process1` FOREIGN KEY (`adoption_process_id`) REFERENCES `adoption_process` (`adoption_process_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adoption_step_date`
--

LOCK TABLES `adoption_step_date` WRITE;
/*!40000 ALTER TABLE `adoption_step_date` DISABLE KEYS */;
INSERT INTO `adoption_step_date` VALUES (45,1,'2022-08-18 09:52:13',38),(46,2,'2022-08-18 23:50:00',38),(47,1,'2022-08-09 10:39:04',39),(49,1,'2022-08-18 13:38:59',40),(50,1,'2022-08-18 19:56:55',41),(51,1,'2022-08-18 22:45:54',42),(69,2,'2022-08-18 23:30:00',42),(70,2,'2022-08-18 23:30:00',41),(71,1,'2022-08-18 23:30:49',44),(74,1,'2022-08-19 00:59:17',46),(77,2,'2022-08-10 02:03:50',39),(78,3,'2022-08-12 02:04:00',39),(79,4,'2022-08-14 02:04:08',39),(80,5,'2022-08-17 02:04:16',39),(81,6,'2022-08-19 02:04:44',39);
/*!40000 ALTER TABLE `adoption_step_date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `answer_id` bigint NOT NULL AUTO_INCREMENT,
  `idx` int DEFAULT NULL,
  `answer` varchar(300) DEFAULT NULL,
  `application_id` bigint DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `fk_answer_application1_idx` (`application_id`),
  CONSTRAINT `fk_answer_application1` FOREIGN KEY (`application_id`) REFERENCES `application` (`application_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=842 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (692,0,'아니요',53),(693,0,'강아지 그릇, 사료, 목줄 등등',53),(694,0,'강형욱님 동영상 보고 부족한 부분을 참고할거에요',53),(695,0,'네',53),(696,0,'네',53),(697,0,'아니요',53),(698,0,'하루에 1시간',53),(699,0,'최대 6시간정도 비웁니다',53),(700,0,'네',53),(701,0,'아니요',53),(702,0,'강아지호텔이나 지인에게 맡길 예정입니다.',53),(703,0,'아파트',53),(704,0,'저입니다.',53),(705,0,'네',53),(706,0,'네',53),(707,0,'',54),(708,0,'',54),(709,0,'',54),(710,0,'',54),(711,0,'',54),(712,0,'',54),(713,0,'',54),(714,0,'',54),(715,0,'',54),(716,0,'',54),(717,0,'',54),(718,0,'',54),(719,0,'',54),(720,0,'',54),(721,0,'',54),(722,0,'아니요 반려 경험은 없습니다! ',55),(723,0,'울타리가 필요하다고 알고 있습니다 또한 강아지가 마실 물을 담는 물통과 밥을 줄 통이 필요합니다! 또한 배변 패드도 필요하다고 알고있습니다',55),(724,0,'우선 전 재택 근무를 주로 하기 때문에 거의 집에서 생활합니다. 따라서 강아지와 많은 시간을 함께 보낼 수 있고 강아지의 스트레스를 위해 매일 산책을 한 시간씩 해줄 예정입니다',55),(725,0,'주로 집에 있기 때문에 가능합니다',55),(726,0,'네 강아지도 저의 가족이라는 생각으로 병원비를 아깝지 않게 사용할 수 있습니다',55),(727,0,'검사는 해보지 않았지만 강아지 카페 등 많은 강아지들과 접족하였을 때 알러지 반응이 없었습니다',55),(728,0,'한 시간~두 시간 정도 생각하고 있습니다',55),(729,0,'거의 비우지 않습니다 많이 비워도 하루에 한시간 정도 밖에 비우지 않습니다',55),(730,0,'당연히 염두하고 있습니다.\n그 부분을 해결하기 위해 강아지 관련 서적이나 영상을 보며 공부하고 있습니다!',55),(731,0,'이번이 처음 키우는 반려동물입니다',55),(732,0,'주변에 반려견을 키우는 친구들이 많아서 일이 있을 시 서로 맡기기로 합의하였습니다. 또한 집 3분거리에 강아지 카페가 있습니다',55),(733,0,'현재 투룸에서 생활하고 있어 강아지 방으로 한 방을 제공할 예정입니다. ',55),(734,0,'주 양육자는 저고 제 2보호자는 일주일에 두번 저희 집에서 자고 가는 저의 동생입니다',55),(735,0,'현재 혼자 살고 있고 가족들은 모두 강아지를 좋아하기 때문에 입양에 찬성하는 입장입니다',55),(736,0,'네 깊이 고민 해보았습니다. 저의 새로운 가족처럼 여기고 아끼며 돌보겠습니다 ',55),(737,0,'아니요 처음입니다!',56),(738,0,'우선 배변 패드, 밥 그릇, 물 그릇, 목줄, 사료 등이 있습니다!',56),(739,0,'저는 개발자 교육을 듣고 있기 때문에 하루의 대부분을 집에서 보내고 있습니다! 몽이를 입양한다면 몽이와 많은 시간을 보내겠습니다!',56),(740,0,'',56),(741,0,'',56),(742,0,'',56),(743,0,'',56),(744,0,'',56),(745,0,'',56),(746,0,'',56),(747,0,'',56),(748,0,'',56),(749,0,'',56),(750,0,'',56),(751,0,'몽이를 진짜 가족이라고 생각하며 몽이를 기르겠습니다!',56),(752,0,'',57),(753,0,'',57),(754,0,'',57),(755,0,'',57),(756,0,'',57),(757,0,'',57),(758,0,'',57),(759,0,'',57),(760,0,'',57),(761,0,'',57),(762,0,'',57),(763,0,'',57),(764,0,'',57),(765,0,'',57),(766,0,'',57),(782,0,'',59),(783,0,'',59),(784,0,'',59),(785,0,'',59),(786,0,'',59),(787,0,'',59),(788,0,'',59),(789,0,'',59),(790,0,'',59),(791,0,'',59),(792,0,'',59),(793,0,'',59),(794,0,'',59),(795,0,'',59),(796,0,'',59),(812,0,'아니용',61),(813,0,'아아',61),(814,0,'오오',61),(815,0,'우엥',61),(816,0,'히잇',61),(817,0,'가가',61),(818,0,'고고',61),(819,0,'구구',61),(820,0,'나나',61),(821,0,'하하',61),(822,0,'호호',61),(823,0,'헤헷',61),(824,0,'하핫',61),(825,0,'넹',61),(826,0,'네!!!',61);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `application_id` bigint NOT NULL AUTO_INCREMENT,
  `board_id` bigint DEFAULT NULL,
  `send` tinyint DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `birth` varchar(10) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `job` varchar(30) DEFAULT NULL,
  `zonecode` char(5) DEFAULT NULL,
  `road_address` varchar(50) DEFAULT NULL,
  `detail_address` varchar(50) DEFAULT NULL,
  `etc` varchar(100) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `application_status` varchar(30) DEFAULT NULL,
  `user_seq` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `phone_number` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  KEY `fk_application_user1_idx` (`user_seq`),
  KEY `FKgpgskw54auwcx8ifk73pyhab1` (`user_id`),
  CONSTRAINT `fk_application_user1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE,
  CONSTRAINT `FKgpgskw54auwcx8ifk73pyhab1` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (53,25,1,'이연정','','','','','','','','2022-08-18 09:52:13','2',15,NULL,''),(54,24,1,'황희원','19981222','W','회사원','42260','대구 수성구 고모로 31 (가천동)','12-34','','2022-08-18 10:39:04','6',17,NULL,'01012345678'),(55,21,1,'고광','','','','','','','','2022-08-18 13:38:59','7',18,NULL,''),(56,25,1,'멀봐','','','','','','','','2022-08-18 19:56:55','7',19,NULL,''),(57,25,1,'고광','','','','39188','경북 구미시 산호대로24길 31-6 (옥계동)','명품타운','','2022-08-18 22:45:54','2',18,NULL,''),(59,25,1,'멀봐','','','','','','','','2022-08-18 23:30:49','1',19,NULL,''),(61,38,1,'이연정','20000103','W','개발자','42753','대구 달서구 조암로6길 5-5 (월성동)','101-2202','','2022-08-19 00:59:17','1',23,NULL,'01034568723');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `board_id` bigint NOT NULL AUTO_INCREMENT,
  `board_tag` varchar(45) NOT NULL,
  `contents` varchar(1000) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_seq` bigint NOT NULL,
  PRIMARY KEY (`board_id`),
  KEY `fk_board_user1_idx` (`user_seq`),
  CONSTRAINT `fk_board_user1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (21,'입양','귀여운 보리 데려가세요???\n\n입양 신청서 작성해주세요~~~!!','2022-08-17 20:31:38',14),(22,'후기','우리 강아지 돌사진\n\n넘 귀엽죠…\n\n요즘 하루가 넘 행복하네요…?','2022-08-17 20:43:14',15),(23,'후기','드디어 또리를 데려왔습니다~???\n\n잘 지내보자 또리야??????','2022-08-17 20:43:17',14),(24,'입양','우리 테디가 최고\n\n넘 귀엽고 사랑스럽고 다해…?❤️?','2022-08-17 22:22:25',15),(25,'입양','?몽이의 평생 가족을 찾습니다?\n\n2022년 6월 20일 대구 달서구 앞산 전망대 근처에서 구조된 아이입니다.\n \n✔ 짖음이 거의 없는 편인데 가끔 짖는 걸 들어보면 목소리가 큰 편이예요\n✔ 아침 저녁 약을 먹고, 발에 바르는 약도 바르고 있습니다.\n✔ 분리불안 훈련이 필요한 상태입니다.\n\n상처가 많은 아이이니 신중하게 결정해서 신청해주세요','2022-08-18 02:21:50',14),(26,'입양','? 홍시는 배변도 완벽하고 앉아, 손, 엎드려, 기다려, 하우스까지 가능한 천재 강아지에요\n\n? 사람도 친구들도 좋아하는 사회성 만렙이고, 아직 아가라 장난이 좋은 시기지만 눈치가 빨라 훈련이 금방 돼요❕\n\n한창 엄마 품이 그리울 아기들인데도 헛짖음, 분리불안도 없이 잘 지내고 있어요?','2022-08-18 21:47:34',21),(27,'입양','우리 헤이의 손을 꽉 잡아주실 마지막 가족을 찾아요?\n\n배변 잘가림\n8세 추정 건강체질\n우측다리 좋지 않음. 근력 운동 필요.\n매우 착하고 얌전하고 순한 성격\n입질X 짖음X 분리불안X\n\n비록 아기 강아지들처럼 새까만 눈동자가 아니더라도, 튼튼한 다리로 우다다다 빠르게 달릴수 없더라도, \n왕방울만한 예쁜 눈으로 세상을 구경하고 느리지만 열심히 총총 달려오는,\n그 자체로 충분히 완벽하게 사랑스러운 강아지입니다.?❤','2022-08-18 21:48:18',14),(28,'입양','? 무무는 사람을 매우 좋아해서 임보자님이 움직이실 때마다 꼬리펠라를 달고 졸졸 따라다녀요ଘ(੭*ˊᵕˋ)੭* 임보자님이 움직이지 않으실 때는 무무도 따라 가만히 누워있는 아주 얌전한 아이에요❣ 집에서 짖음도 없답니다ㅎㅎ\n\n배변훈련도 아주 잘 되어있으며 산책을 가거나 애견카페를 가면 여기저기 냄새도 맡고 활발한 아이에요!\n\n하지만 사회성이 조금 부족한 편이라 가끔 다른 강아지에게 으르렁거리면서 예민해질 때가 있어요?\n\n?\n무무와 잘 맞는 아가를 찾기가 힘들 것 같아 외동으로 입양을 갔으면 합니다!','2022-08-18 21:54:31',21),(30,'잡담','여러분 짱절미 보셨나요?\n\n진짜 짱귀여워요....눈물 광광','2022-08-18 22:03:48',21),(31,'잡담','슬라이드 계단 아직 정착안하신 분들 있나요??\n\n아르르 논슬립 슬라이드 계단 진짜 좋은것 같아요...!\n\n추천합니다~~ 저희 강아지도 좋아해요?','2022-08-18 22:09:28',21),(32,'입양','뛰어 노는 것도 다 좋지만 엄마가 꼭 안아주는걸 제일 좋아하는 6개월 아가몽이예요.\n\n요미에게는 가족의 따스한 품이 너무나 필요합니다.\n\n✔ 차후 중성화 수술은 필수이고, 비용이 발생하며, 중성화 수술을 반대하는 경우에 입양 상담조차 진행하지 않습니다.','2022-08-18 22:09:50',14),(33,'후기','우리 노마 그려봤어요?\n\n너무 귀엽죠!! ㅋㅋㅋㅋ','2022-08-18 22:19:40',16),(34,'후기','방금 자고 일어났어요..\n\n너무 푹 잔 나머지 얼굴이 꾸깃꾸깃..ㅋㅋㅋ 산책 다녀와서 씻겨야겠어요?','2022-08-18 22:26:55',16),(35,'잡담','강아지 간식 추천!\n\n저희 아기가 가장 좋아하는 간식이예요. 나쁜 성분도 없고 너무 좋아요~\n허겁지겁 먹는게 걱정이라 사봤는데 먹는데 3~5분정도 걸리는 것 같아요. 치석제거에도 도움이 됩니다! ^-^b','2022-08-18 22:35:21',16),(36,'잡담','비가 너무 많이와서 산책을 나갈수가 없네요???\n\n하염없이 창밖만 바라보는 중..','2022-08-18 22:38:40',22),(37,'잡담','강아지 혈압 이렇게 재는거 아셨나요?\n\n진짜 졸귀탱구리구리!!!!ㅠㅠㅠㅠ','2022-08-18 22:41:06',23),(38,'입양','?버니는 애교 많고 사람을 아주 좋아하는 강아지에요!\n\n자기표현 당차게 할 줄 알고, 같이 놀고 싶어 치대는 발랄함을 가지고 있습니다?✨\n\n?눈치가 빠르고 똘똘해서 손 같은 기본 훈련도 잘 되어있습니다..!\n\n?아직 조금은 산만하고 까불지도 모르는 특성을 잘 이해해주시고, 기다려주며 더 넓은 세상을 알려주실 평생 가족을 찾습니다\n\n','2022-08-19 00:25:36',15),(39,'잡담','저희 강아지 보고가세요..~~\n\n넘 귀엽죠^-^\n\n전 매일 본답니다~~❤️','2022-08-19 00:40:36',23),(40,'잡담','오늘의 힐링짤???\n귀여운 갱얼쥐 보고가세요~!','2022-08-19 00:40:55',14);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board_tag`
--

DROP TABLE IF EXISTS `board_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_tag` (
  `board_tag_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`board_tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_tag`
--

LOCK TABLES `board_tag` WRITE;
/*!40000 ALTER TABLE `board_tag` DISABLE KEYS */;
INSERT INTO `board_tag` VALUES (1,'입양'),(2,'후기'),(3,'잡담');
/*!40000 ALTER TABLE `board_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_log`
--

DROP TABLE IF EXISTS `chat_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_log` (
  `chat_log_id` bigint NOT NULL AUTO_INCREMENT,
  `user_seq` bigint DEFAULT NULL,
  `chat_content` text,
  `createtime` datetime DEFAULT NULL,
  `chat_room_id` bigint NOT NULL,
  `user_nickname` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `is_request` bit(1) DEFAULT NULL,
  PRIMARY KEY (`chat_log_id`),
  KEY `fk_chat_log_chat_room1_idx` (`chat_room_id`),
  CONSTRAINT `fk_chat_log_chat_room1` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`chat_room_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_log`
--

LOCK TABLES `chat_log` WRITE;
/*!40000 ALTER TABLE `chat_log` DISABLE KEYS */;
INSERT INTO `chat_log` VALUES (106,18,NULL,'2022-08-18 13:40:20',31,NULL,'안녕하세요!',NULL);
/*!40000 ALTER TABLE `chat_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_room`
--

DROP TABLE IF EXISTS `chat_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_room` (
  `chat_room_id` bigint NOT NULL AUTO_INCREMENT,
  `protector_id` bigint DEFAULT NULL,
  `applicant_id` bigint DEFAULT NULL,
  `protector_nickname` varchar(100) DEFAULT NULL,
  `applicant_nickname` varchar(100) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `adoption_process_id` bigint DEFAULT NULL,
  `application_id` bigint DEFAULT NULL,
  PRIMARY KEY (`chat_room_id`),
  KEY `fk_chat_room_adoption_process1_idx` (`adoption_process_id`),
  CONSTRAINT `fk_chat_room_adoption_process1` FOREIGN KEY (`adoption_process_id`) REFERENCES `adoption_process` (`adoption_process_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_room`
--

LOCK TABLES `chat_room` WRITE;
/*!40000 ALTER TABLE `chat_room` DISABLE KEYS */;
INSERT INTO `chat_room` VALUES (29,14,15,'이면','이연정','2022-08-18 09:52:13',38,NULL),(30,15,17,'이연정','히오니소스','2022-08-18 10:39:04',39,NULL),(31,14,18,'이면','고광','2022-08-18 13:38:59',40,NULL),(32,14,19,'이연정','멀봐','2022-08-18 19:56:55',41,NULL),(33,14,18,'이먄','고광','2022-08-18 22:45:54',42,NULL),(35,14,19,'이먄','멀봐','2022-08-18 23:30:49',44,NULL),(37,15,23,'이연정','당돌한얼음','2022-08-19 00:59:17',46,NULL);
/*!40000 ALTER TABLE `chat_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` bigint NOT NULL AUTO_INCREMENT,
  `user_seq` bigint DEFAULT NULL,
  `user_nickname` varchar(100) DEFAULT NULL,
  `contents` varchar(200) DEFAULT NULL,
  `secret` tinyint(1) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `board_id` bigint NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_comment_board1_idx` (`board_id`),
  CONSTRAINT `fk_comment_board1` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (35,14,'LMH','연락 주세요!',0,'2022-08-17 20:39:46',21),(36,15,'LMH','혹시 경기도 어디인가요?',0,'2022-08-17 20:40:54',21),(37,14,'LMH','너무 귀여워요!',0,'2022-08-17 20:44:00',22),(38,15,'LMH','또리 넘 귀엽네요~~~^^행복하세요❤️',0,'2022-08-17 20:45:26',23),(39,17,NULL,'dffsdf',0,'2022-08-18 09:05:30',25),(40,17,NULL,'안녕하세요',0,'2022-08-18 10:46:02',24),(41,17,NULL,'몽이 건강은 괜찮은가용??',1,'2022-08-18 10:46:27',24),(42,18,NULL,'너무 예쁘네요!',0,'2022-08-18 13:28:45',21),(43,14,NULL,'너무 귀여워요!!!! ???',0,'2022-08-18 22:10:55',30),(44,16,NULL,'이거 진짜 좋아요! 완전 강추',0,'2022-08-18 22:30:13',31),(45,21,NULL,'ㅋㅋㅋㅋㅋㅋㅋ 넘 귀엽네요^-^',0,'2022-08-18 22:31:20',34),(46,22,NULL,'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',0,'2022-08-18 22:39:42',33),(47,22,NULL,'ㅠㅜㅠㅜㅠ 뭐야뭐야 너무귀여어요 ㅠㅜㅠ',0,'2022-08-18 22:41:36',37),(48,14,NULL,'버니버니버니버니당근당근',0,'2022-08-19 00:34:30',38),(49,23,NULL,'?????',0,'2022-08-19 00:35:11',38);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dog_info`
--

DROP TABLE IF EXISTS `dog_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dog_info` (
  `dog_info_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `area_sido` varchar(20) DEFAULT NULL,
  `area_gugun` varchar(20) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `age` varchar(20) DEFAULT NULL,
  `weight` varchar(10) DEFAULT NULL,
  `breed` varchar(45) DEFAULT NULL,
  `vaccination` tinyint DEFAULT NULL,
  `neutering` tinyint DEFAULT NULL,
  `adoption_status` tinyint DEFAULT NULL,
  `board_id` bigint DEFAULT NULL,
  PRIMARY KEY (`dog_info_id`),
  KEY `fk_dog_info_board1_idx` (`board_id`),
  CONSTRAINT `fk_dog_info_board1` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dog_info`
--

LOCK TABLES `dog_info` WRITE;
/*!40000 ALTER TABLE `dog_info` DISABLE KEYS */;
INSERT INTO `dog_info` VALUES (11,'보리','경기도',NULL,'암컷','3','3kg','모름',0,1,0,21),(12,'테디','캐나다',NULL,'암컷','9','15kg','모름',0,1,1,24),(13,'몽이','대구',NULL,'수컷','1세','1kg','말티즈',0,0,0,25),(14,'홍시','구미',NULL,'수컷','2개월','3kg','차우차우',0,0,0,26),(15,'헤이','경기도',NULL,'암컷','8세','3kg','스피츠',0,0,0,27),(16,'무무','서울',NULL,'암컷','4살','6kg','믹스',0,1,0,28),(17,'요미','경남 김해',NULL,'암컷','6개월','1kg','요크셔테리어',0,0,0,32),(18,'버니','구미',NULL,'암컷','3','4kg','시고르자브종',0,0,0,38);
/*!40000 ALTER TABLE `dog_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dog_nature`
--

DROP TABLE IF EXISTS `dog_nature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dog_nature` (
  `dog_nature_id` bigint NOT NULL AUTO_INCREMENT,
  `dog_info_id` bigint NOT NULL,
  `nature1` varchar(255) DEFAULT NULL,
  `nature2` varchar(255) DEFAULT NULL,
  `nature3` varchar(255) DEFAULT NULL,
  `nature4` varchar(255) DEFAULT NULL,
  `nature5` varchar(255) DEFAULT NULL,
  `nature6` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dog_nature_id`),
  KEY `fk_dog_nature_dog_info1_idx` (`dog_info_id`),
  CONSTRAINT `fk_dog_nature_dog_info1` FOREIGN KEY (`dog_info_id`) REFERENCES `dog_info` (`dog_info_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dog_nature`
--

LOCK TABLES `dog_nature` WRITE;
/*!40000 ALTER TABLE `dog_nature` DISABLE KEYS */;
INSERT INTO `dog_nature` VALUES (11,11,'2','4','5','1','1','5'),(12,12,'4','4','3','1','3','4'),(13,13,'2','4','4','1','2','5'),(14,14,'4','4','4','2','4','5'),(15,15,'3','3','4','1','1','3'),(16,16,'3','4','2','4','4','4'),(17,17,'2','2','4','2','1','4'),(18,18,'4','4','5','3','3','4');
/*!40000 ALTER TABLE `dog_nature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_result`
--

DROP TABLE IF EXISTS `game_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_result` (
  `score_id` bigint NOT NULL AUTO_INCREMENT,
  `game_tag` varchar(45) DEFAULT NULL,
  `result` tinyint DEFAULT NULL,
  `user_seq` bigint NOT NULL,
  PRIMARY KEY (`score_id`),
  KEY `fk_score_user_idx` (`user_seq`),
  CONSTRAINT `fk_score_user` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_result`
--

LOCK TABLES `game_result` WRITE;
/*!40000 ALTER TABLE `game_result` DISABLE KEYS */;
INSERT INTO `game_result` VALUES (14,'1',1,15),(16,'2',1,17),(17,'1',1,17),(18,'2',1,18),(19,'1',1,18),(20,'1',1,19),(21,'2',1,15),(22,'2',1,14),(23,'1',1,14),(24,'2',1,16),(25,'1',1,16);
/*!40000 ALTER TABLE `game_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_tag`
--

DROP TABLE IF EXISTS `game_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_tag` (
  `game_tag_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`game_tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_tag`
--

LOCK TABLES `game_tag` WRITE;
/*!40000 ALTER TABLE `game_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `game_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_storage`
--

DROP TABLE IF EXISTS `image_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_storage` (
  `image_storage_id` bigint NOT NULL AUTO_INCREMENT,
  `orders` int DEFAULT NULL,
  `origin_filename` varchar(1000) DEFAULT NULL,
  `server_path` varchar(1000) DEFAULT NULL,
  `save_filename` varchar(1000) DEFAULT NULL,
  `board_id` bigint DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`image_storage_id`),
  KEY `fk_image_storage_board1_idx` (`board_id`),
  CONSTRAINT `fk_image_storage_board1` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_storage`
--

LOCK TABLES `image_storage` WRITE;
/*!40000 ALTER TABLE `image_storage` DISABLE KEYS */;
INSERT INTO `image_storage` VALUES (35,1,'7bb92236d14d46a18666745081c877ce220817.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/7bb92236d14d46a18666745081c877ce220817.jpg','7bb92236d14d46a18666745081c877ce220817.jpg',21,NULL),(36,2,'ef00c2e61e2747ab8d419a606565ac93220817.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/ef00c2e61e2747ab8d419a606565ac93220817.jpg','ef00c2e61e2747ab8d419a606565ac93220817.jpg',21,NULL),(37,3,'fbbbae9151334960a30350af332786b9220817.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/fbbbae9151334960a30350af332786b9220817.jpg','fbbbae9151334960a30350af332786b9220817.jpg',21,NULL),(38,1,'a776bb5b2fbc4182b65051dffd12a360220817.jpeg','https://i7d209.p.ssafy.io:8081/api/v1/image/a776bb5b2fbc4182b65051dffd12a360220817.jpeg','a776bb5b2fbc4182b65051dffd12a360220817.jpeg',22,NULL),(39,1,'11c42bfb02384ade8f4d846d70a0eef8220817.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/11c42bfb02384ade8f4d846d70a0eef8220817.jpg','11c42bfb02384ade8f4d846d70a0eef8220817.jpg',23,NULL),(40,1,'434edd29f49a4d58a85cbb8f9dd70ce2220817.jpeg','https://i7d209.p.ssafy.io:8081/api/v1/image/434edd29f49a4d58a85cbb8f9dd70ce2220817.jpeg','434edd29f49a4d58a85cbb8f9dd70ce2220817.jpeg',24,NULL),(41,1,'6112b0a5d5454ff9b31d0281cc436be9220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/6112b0a5d5454ff9b31d0281cc436be9220818.jpg','6112b0a5d5454ff9b31d0281cc436be9220818.jpg',25,NULL),(42,2,'f3552b38ad3741589b9b6fe6291f4466220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/f3552b38ad3741589b9b6fe6291f4466220818.jpg','f3552b38ad3741589b9b6fe6291f4466220818.jpg',25,NULL),(43,3,'1612926e0c6b4b329c8d645a229e23e9220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/1612926e0c6b4b329c8d645a229e23e9220818.jpg','1612926e0c6b4b329c8d645a229e23e9220818.jpg',25,NULL),(44,1,'eeae8e39e2054dd7ad729222a093c8a4220818.png','https://i7d209.p.ssafy.io:8081/api/v1/image/eeae8e39e2054dd7ad729222a093c8a4220818.png','eeae8e39e2054dd7ad729222a093c8a4220818.png',26,NULL),(45,2,'32481bf8c9a645e885e9206385fe53de220818.png','https://i7d209.p.ssafy.io:8081/api/v1/image/32481bf8c9a645e885e9206385fe53de220818.png','32481bf8c9a645e885e9206385fe53de220818.png',26,NULL),(46,1,'c38cf378643e4fd1855858b7972f5503220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/c38cf378643e4fd1855858b7972f5503220818.jpg','c38cf378643e4fd1855858b7972f5503220818.jpg',27,NULL),(47,2,'ba5edafc6eb343bb8338f21091903652220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/ba5edafc6eb343bb8338f21091903652220818.jpg','ba5edafc6eb343bb8338f21091903652220818.jpg',27,NULL),(48,3,'b2ce207e993a4f3eaa2a546a113fed94220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/b2ce207e993a4f3eaa2a546a113fed94220818.jpg','b2ce207e993a4f3eaa2a546a113fed94220818.jpg',27,NULL),(49,1,'9a05e82e28114eeea4ff113955937894220818.png','https://i7d209.p.ssafy.io:8081/api/v1/image/9a05e82e28114eeea4ff113955937894220818.png','9a05e82e28114eeea4ff113955937894220818.png',28,NULL),(50,2,'0a8c5618bbd64fbeb317f56e719432b1220818.png','https://i7d209.p.ssafy.io:8081/api/v1/image/0a8c5618bbd64fbeb317f56e719432b1220818.png','0a8c5618bbd64fbeb317f56e719432b1220818.png',28,NULL),(51,3,'5e16d7b796914abd9eef47a9c3c983ab220818.png','https://i7d209.p.ssafy.io:8081/api/v1/image/5e16d7b796914abd9eef47a9c3c983ab220818.png','5e16d7b796914abd9eef47a9c3c983ab220818.png',28,NULL),(52,1,'465560c27bb141ad8a489670fb794929220818.png','https://i7d209.p.ssafy.io:8081/api/v1/image/465560c27bb141ad8a489670fb794929220818.png','465560c27bb141ad8a489670fb794929220818.png',30,NULL),(53,1,'d0e56593529f437dbed90582c279bbce220818.png','https://i7d209.p.ssafy.io:8081/api/v1/image/d0e56593529f437dbed90582c279bbce220818.png','d0e56593529f437dbed90582c279bbce220818.png',31,NULL),(54,1,'34e974851f244c7d9a55483ae6c865e3220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/34e974851f244c7d9a55483ae6c865e3220818.jpg','34e974851f244c7d9a55483ae6c865e3220818.jpg',32,NULL),(55,2,'fb2a68354c9d4152bda32384be4fb79b220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/fb2a68354c9d4152bda32384be4fb79b220818.jpg','fb2a68354c9d4152bda32384be4fb79b220818.jpg',32,NULL),(56,3,'bc75d1cfed6a4f1e9f45ba1314324079220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/bc75d1cfed6a4f1e9f45ba1314324079220818.jpg','bc75d1cfed6a4f1e9f45ba1314324079220818.jpg',32,NULL),(57,1,'1a312ffbff5b4c9f918581a9703cefc0220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/1a312ffbff5b4c9f918581a9703cefc0220818.jpg','1a312ffbff5b4c9f918581a9703cefc0220818.jpg',33,NULL),(58,2,'f7affd98a4b143fd9eaedef651104ee0220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/f7affd98a4b143fd9eaedef651104ee0220818.jpg','f7affd98a4b143fd9eaedef651104ee0220818.jpg',33,NULL),(59,1,'7724b8dd844f485fa5424f09d6d28954220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/7724b8dd844f485fa5424f09d6d28954220818.jpg','7724b8dd844f485fa5424f09d6d28954220818.jpg',34,NULL),(60,2,'bba40be12bf84f19a2af4dda50ab90b1220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/bba40be12bf84f19a2af4dda50ab90b1220818.jpg','bba40be12bf84f19a2af4dda50ab90b1220818.jpg',34,NULL),(61,1,'304dfb373df14b8fbe622da4fab58d91220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/304dfb373df14b8fbe622da4fab58d91220818.jpg','304dfb373df14b8fbe622da4fab58d91220818.jpg',35,NULL),(62,1,'879e059a506d425d82d58d2092bb84aa220818.png','https://i7d209.p.ssafy.io:8081/api/v1/image/879e059a506d425d82d58d2092bb84aa220818.png','879e059a506d425d82d58d2092bb84aa220818.png',36,NULL),(63,2,'a0b6a34f33a344eca27d13b5eefca1e0220818.png','https://i7d209.p.ssafy.io:8081/api/v1/image/a0b6a34f33a344eca27d13b5eefca1e0220818.png','a0b6a34f33a344eca27d13b5eefca1e0220818.png',36,NULL),(64,1,'20e80d6b382e41dabace368f8cedbc6c220818.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/20e80d6b382e41dabace368f8cedbc6c220818.jpg','20e80d6b382e41dabace368f8cedbc6c220818.jpg',37,NULL),(65,1,'20e7b7eb9c8840bf97f4fa5a7af9363b220819.png','https://i7d209.p.ssafy.io:8081/api/v1/image/20e7b7eb9c8840bf97f4fa5a7af9363b220819.png','20e7b7eb9c8840bf97f4fa5a7af9363b220819.png',38,NULL),(66,2,'83ec2ef293b74e9897fc217ee0cb6240220819.png','https://i7d209.p.ssafy.io:8081/api/v1/image/83ec2ef293b74e9897fc217ee0cb6240220819.png','83ec2ef293b74e9897fc217ee0cb6240220819.png',38,NULL),(67,1,'45d382496e114d2ca6d8c403a056dcb7220819.png','https://i7d209.p.ssafy.io:8081/api/v1/image/45d382496e114d2ca6d8c403a056dcb7220819.png','45d382496e114d2ca6d8c403a056dcb7220819.png',39,NULL),(68,1,'418edb976bda4029afb5810dbb45dac0220819.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/418edb976bda4029afb5810dbb45dac0220819.jpg','418edb976bda4029afb5810dbb45dac0220819.jpg',40,NULL),(69,2,'4b5032ea666243b2bb2424fbe6f61976220819.jpg','https://i7d209.p.ssafy.io:8081/api/v1/image/4b5032ea666243b2bb2424fbe6f61976220819.jpg','4b5032ea666243b2bb2424fbe6f61976220819.jpg',40,NULL);
/*!40000 ALTER TABLE `image_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `like_id` bigint NOT NULL AUTO_INCREMENT,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_seq` bigint NOT NULL,
  `board_id` bigint NOT NULL,
  PRIMARY KEY (`like_id`),
  KEY `fk_likes_user1_idx` (`user_seq`),
  KEY `fk_likes_board1_idx` (`board_id`),
  CONSTRAINT `fk_likes_board1` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_likes_user1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (20,'2022-08-17 20:46:00',15,23),(21,'2022-08-17 20:46:56',14,21),(22,'2022-08-18 01:15:24',14,24),(23,'2022-08-18 02:47:30',17,25),(24,'2022-08-18 02:49:39',17,22),(25,'2022-08-18 10:47:28',17,24),(26,'2022-08-18 21:50:37',14,27),(27,'2022-08-18 21:50:40',14,26),(28,'2022-08-18 21:50:41',14,25),(29,'2022-08-18 21:50:44',14,23),(30,'2022-08-18 21:50:45',14,22),(31,'2022-08-18 22:10:19',14,32),(32,'2022-08-18 22:10:34',14,31),(33,'2022-08-18 22:10:39',14,30),(34,'2022-08-18 22:20:22',16,33),(35,'2022-08-18 22:20:25',16,32),(36,'2022-08-18 22:20:27',16,31),(37,'2022-08-18 22:20:30',16,30),(38,'2022-08-18 22:20:32',16,28),(39,'2022-08-18 22:20:34',16,27),(40,'2022-08-18 22:20:35',16,26),(41,'2022-08-18 22:27:05',16,34),(42,'2022-08-18 22:30:26',21,33),(43,'2022-08-18 22:30:37',21,26),(44,'2022-08-18 22:38:54',22,36),(45,'2022-08-18 22:38:57',22,35),(46,'2022-08-18 22:39:07',22,21),(47,'2022-08-18 22:39:11',22,22),(48,'2022-08-18 22:39:14',22,23),(49,'2022-08-18 22:39:16',22,24),(50,'2022-08-18 22:39:18',22,25),(51,'2022-08-18 22:39:19',22,26),(52,'2022-08-18 22:41:24',22,37),(53,'2022-08-19 00:41:16',14,39),(54,'2022-08-19 00:41:22',14,40),(55,'2022-08-19 00:41:28',14,38),(56,'2022-08-19 00:41:30',23,40),(57,'2022-08-19 00:41:59',16,38);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meeting_room`
--

DROP TABLE IF EXISTS `meeting_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meeting_room` (
  `meeting_room_id` bigint NOT NULL AUTO_INCREMENT,
  `host_id` bigint DEFAULT NULL,
  `active` tinyint DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `adoption_process_id` bigint NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `session_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`meeting_room_id`),
  KEY `fk_meeting_room_adoption_process1_idx` (`adoption_process_id`),
  CONSTRAINT `fk_meeting_room_adoption_process1` FOREIGN KEY (`adoption_process_id`) REFERENCES `adoption_process` (`adoption_process_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meeting_room`
--

LOCK TABLES `meeting_room` WRITE;
/*!40000 ALTER TABLE `meeting_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `meeting_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `createtime` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `detail_address` varchar(255) DEFAULT NULL,
  `etc` varchar(255) DEFAULT NULL,
  `gender` char(1) NOT NULL,
  `job` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `road_address` varchar(255) DEFAULT NULL,
  `zonecode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply` (
  `reply_id` bigint NOT NULL AUTO_INCREMENT,
  `user_seq` bigint DEFAULT NULL,
  `user_nickname` varchar(100) DEFAULT NULL,
  `contents` varchar(200) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_id` bigint NOT NULL,
  `secret` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`reply_id`),
  KEY `fk_reply_comment1_idx` (`comment_id`),
  CONSTRAINT `fk_reply_comment1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (14,15,'LMH',NULL,'2022-08-17 20:44:21',37,0),(15,14,NULL,NULL,'2022-08-18 10:46:10',40,0);
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `score_id` bigint NOT NULL AUTO_INCREMENT,
  `result` int DEFAULT NULL,
  `game_tag_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`score_id`),
  KEY `FK9cqdwnnc262ca2vconr7a1hii` (`game_tag_id`),
  KEY `FKmy7b9lpb68o9qfmf5k2mthakc` (`user_id`),
  CONSTRAINT `FK9cqdwnnc262ca2vconr7a1hii` FOREIGN KEY (`game_tag_id`) REFERENCES `game_tag` (`game_tag_id`),
  CONSTRAINT `FKmy7b9lpb68o9qfmf5k2mthakc` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `star`
--

DROP TABLE IF EXISTS `star`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `star` (
  `star_id` bigint NOT NULL AUTO_INCREMENT,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_seq` bigint NOT NULL,
  `board_id` bigint NOT NULL,
  PRIMARY KEY (`star_id`),
  KEY `fk_star_user1_idx` (`user_seq`),
  KEY `fk_star_board1_idx` (`board_id`),
  CONSTRAINT `fk_star_board1` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_star_user1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `star`
--

LOCK TABLES `star` WRITE;
/*!40000 ALTER TABLE `star` DISABLE KEYS */;
INSERT INTO `star` VALUES (16,'2022-08-18 02:49:24',17,25),(17,'2022-08-18 10:47:26',17,24),(18,'2022-08-18 12:41:35',18,25),(19,'2022-08-18 22:30:33',21,27),(20,'2022-08-18 22:30:33',16,28),(21,'2022-08-18 22:30:36',21,26),(22,'2022-08-18 22:38:58',22,35),(23,'2022-08-18 22:39:08',22,21),(24,'2022-08-19 00:41:42',14,38),(25,'2022-08-19 00:41:59',16,38);
/*!40000 ALTER TABLE `star` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_seq` bigint NOT NULL AUTO_INCREMENT,
  `status` int DEFAULT NULL,
  `email` varchar(512) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `email_verified_yn` varchar(1) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `profile_image_url` varchar(2100) DEFAULT NULL,
  `provider_type` varchar(20) DEFAULT NULL,
  `role_type` varchar(20) DEFAULT NULL,
  `user_id` varchar(64) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `birth` varchar(20) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `job` varchar(45) DEFAULT NULL,
  `zonecode` char(5) DEFAULT NULL,
  `road_address` varchar(100) DEFAULT NULL,
  `detail_address` varchar(100) DEFAULT NULL,
  `etc` varchar(300) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `modified_at` datetime(6) NOT NULL,
  `phone_number` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`user_seq`),
  UNIQUE KEY `UK_a3imlf41l37utmxiquukk8ajc` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (14,1,'mihyun0921@gmail.com','이먄','Y','NO_PASS','https://i7d209.p.ssafy.io:8081/api/v1/image/a68cabfb59ec4b05820a98a7abde54f9220818.jpeg','GOOGLE','USER','115898611593911096816','이미현','','','','','','','','2022-08-17 20:27:27.715051','2022-08-17 20:27:27.715051',''),(15,1,'NO_EMAIL','이연정','Y','NO_PASS','https://i7d209.p.ssafy.io:8081/api/v1/image/5725840987d14f71ae3cf5d33109a3ff220819.jpg','KAKAO','USER','2368209940','이연정','','','','','','','','2022-08-17 20:40:14.836480','2022-08-17 20:40:14.836480',''),(16,1,'NO_EMAIL','이미현','Y','NO_PASS','http://k.kakaocdn.net/dn/u7GkY/btrFWdq6n6m/bi26DzU0W47KLxmJMVQyW0/img_110x110.jpg','KAKAO','USER','2374371005','이미현','19950921','W','대학생','14321','경기 광명시 소하로 162 (소하동, 휴먼시아7단지아파트)','우리집으로가자','','2022-08-17 21:27:47.490569','2022-08-17 21:27:47.490569','01047053090'),(17,1,'huiwon1488@gmail.com','이연정','Y','NO_PASS','https://lh3.googleusercontent.com/a/AItbvmlqIpiTr0vtL1HzfZ9nF4ELm4n8x6IKBXQjb6is=s96-c','GOOGLE','USER','102851349167541512575','황희원','19981222','W','회사원','42260','대구 수성구 고모로 31 (가천동)','12-34','','2022-08-18 01:47:21.937023','2022-08-18 01:47:21.937023','01012345678'),(18,1,'NO_EMAIL','고광','Y','NO_PASS','http://k.kakaocdn.net/dn/FMOfe/btrHEAF9HkS/ZAnEknAnpNPne7m7lz7X3K/img_110x110.jpg','KAKAO','USER','2393148599','고광','','','','39188','경북 구미시 산호대로24길 31-6 (옥계동)','명품타운','','2022-08-18 10:11:30.916713','2022-08-18 10:11:30.916713',''),(19,1,'102132@naver.com','멀봐','Y','NO_PASS','https://ssl.pstatic.net/static/pwe/address/img_profile.png','NAVER','USER','bMrIFbrSxgSBytY4xmE-zYYe562iw_CeImghCo2DDVs','멀봐','','','','','','','','2022-08-18 10:44:48.954294','2022-08-18 10:44:48.954294',''),(20,1,'NO_EMAIL','황희원','Y','NO_PASS','http://k.kakaocdn.net/dn/dp8URa/btrI2pou1Km/ssaJtmwJaRlYp9XDC77W3k/img_110x110.jpg','KAKAO','USER','2373229489','황희원','','','','','','','','2022-08-18 13:23:43.935777','2022-08-18 13:23:43.935777',''),(21,1,'anwlro7958@gmail.com','멍세구','Y','NO_PASS','https://i7d209.p.ssafy.io:8081/api/v1/image/33d43811667245fe85c9e07be7ec6242220818.png','GOOGLE','USER','115047832328972672968','이연정','','','','','','','','2022-08-18 21:39:00.315058','2022-08-18 21:39:00.315058',''),(22,1,'abc20081@naver.com','이미현','Y','NO_PASS','https://ssl.pstatic.net/static/pwe/address/img_profile.png','NAVER','USER','BjVnxX6oA7dsVJAyr5_OPXc7BVb4_8ifRyn2xuVzxhY','이미현','','','','','','','','2022-08-18 21:39:18.013977','2022-08-18 21:39:18.013977',''),(23,1,'anwlro7958@naver.com','당돌한얼음','Y','NO_PASS','https://i7d209.p.ssafy.io:8081/api/v1/image/5858f980dfa048f2a95cca34196fba0b220818.png','NAVER','USER','fp0ZWDYs6oaUPWYbKpCR2FIlye-6CjYqvh1UFOUIzt4','이연정','20000103','W','개발자','42753','대구 달서구 조암로6길 5-5 (월성동)','101-2202','','2022-08-18 22:37:55.019667','2022-08-18 22:37:55.019667','01034568723');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_dog_nature`
--

DROP TABLE IF EXISTS `user_dog_nature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_dog_nature` (
  `user_dog_nature_id` bigint NOT NULL AUTO_INCREMENT,
  `user_seq` bigint NOT NULL,
  `nature1` int DEFAULT NULL,
  `nature2` int DEFAULT NULL,
  `nature3` int DEFAULT NULL,
  `nature4` int DEFAULT NULL,
  `nature5` int DEFAULT NULL,
  `nature6` int DEFAULT NULL,
  PRIMARY KEY (`user_dog_nature_id`),
  KEY `fk_user_dog_nature_user1_idx` (`user_seq`),
  CONSTRAINT `fk_user_dog_nature_user1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_dog_nature`
--

LOCK TABLES `user_dog_nature` WRITE;
/*!40000 ALTER TABLE `user_dog_nature` DISABLE KEYS */;
INSERT INTO `user_dog_nature` VALUES (8,14,6,6,6,6,6,6),(9,16,12,12,15,9,9,11),(10,15,9,10,9,12,9,8),(12,17,11,10,11,10,6,12),(28,18,11,4,8,9,9,9),(34,19,6,12,13,3,6,14),(40,20,10,10,10,7,14,13),(41,21,9,9,10,10,11,8);
/*!40000 ALTER TABLE `user_dog_nature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_refresh_token`
--

DROP TABLE IF EXISTS `user_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_refresh_token` (
  `refresh_token_seq` bigint NOT NULL AUTO_INCREMENT,
  `refresh_token` varchar(256) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  PRIMARY KEY (`refresh_token_seq`),
  UNIQUE KEY `UK_qca3mjxv5a1egwmn4wnbplfkt` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_refresh_token`
--

LOCK TABLES `user_refresh_token` WRITE;
/*!40000 ALTER TABLE `user_refresh_token` DISABLE KEYS */;
INSERT INTO `user_refresh_token` VALUES (26,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY2MTM0MDQ0N30.cyLaacC5AY1f9kDoIiEcObathWw_U8Xh5q05iYJ-9ho','115898611593911096816'),(27,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY2MTM0MTIxNH0.SfWNg5icr08rWzTWQXy-fkTwNwEpF4d9-A83U51zNXY','2368209940'),(28,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY2MTM0NDA2N30.xuGTCnd4VoB5-MSmJXHTz6LwftmLa2o2P2HMn3zvA9E','2374371005'),(29,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY2MTM1OTY0MX0.TG1bzKeOYwl7dh8Y_fL2Ls5WjMe6EdUDQxJ1xylbrZw','102851349167541512575'),(30,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY2MTM4OTg5MH0.KXfE4yIQvWjYoxaegAtvZ1tH8YMiW2kZNHT3orElXM4','2393148599'),(31,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY2MTM5MTg4OH0.AUjNrFS1YN0E6FDOSz28hEY7VeqI2zJmbTGDm-ocTv4','bMrIFbrSxgSBytY4xmE-zYYe562iw_CeImghCo2DDVs'),(32,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY2MTQwMTQyM30.n4b3R84QlMDPdIxWiCqhlyXeZstBbN24G6Po1tgALA8','2373229489'),(33,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY2MTQzMTE0MH0.1ZLQ4PlTIZIOm2n9HmG_ZK1zF8ppJXwRN23r7b90NvQ','115047832328972672968'),(34,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY2MTQzMTE1OH0.5YbsgzGsibp1H--9Uma5KpuU629lvkSfbNF7eGkZ3To','BjVnxX6oA7dsVJAyr5_OPXc7BVb4_8ifRyn2xuVzxhY'),(35,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY2MTQzNDY3NX0.qsMC9FxcQcn9thMah7l4zpYzGHZvp3a91RF4cNc3ML0','fp0ZWDYs6oaUPWYbKpCR2FIlye-6CjYqvh1UFOUIzt4');
/*!40000 ALTER TABLE `user_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19  5:13:20
