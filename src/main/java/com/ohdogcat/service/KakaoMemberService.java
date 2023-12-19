package com.ohdogcat.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.repository.KakaoMemberDao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoMemberService {

    private final KakaoMemberDao kakaoMemberDao; // 의존성 주입(ReqiredArgsConstructor)

    public String getAccessToken(String authorize_code) { // 접속 토큰 가져오기
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=ec9680b53803006c0f80b409abf45501"); // 본인이 발급받은 key
            sb.append(
                "&redirect_uri=http://localhost:8081/ohdogcat/user/signin/kakaoLogin"); // 본인이 설정한
            // 주소
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();
            int responseCode = conn.getResponseCode();

            // responseCode 데이터 값 확인
            log.debug("responseCode={}", responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }

            log.debug("response body={}", result);

            JsonParser parser = new JsonParser(); // deprecated 메서드
            JsonElement element = parser.parse(result);
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            log.debug("access_token={}", access_Token);
            log.debug("refresh_token={}", refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_Token;
    }

    public MemberSessionDto getUserInfo(String access_Token) { // 토큰에서 값 가져오기
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            int responseCode = conn.getResponseCode();

            log.debug("responseCode={}", responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }

            // result 데이터 값 확인
            log.debug("response body={}", result);

            JsonParser parser = new JsonParser(); // deprecated 메서드
            JsonElement element = parser.parse(result);
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account")
                .getAsJsonObject();
            String kakao_client_id = element.getAsJsonObject().get("id").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            String member_id = properties.get("nickname").getAsString();

            log.debug("properties={}, kakao_account={}, kakao_client_id={}, email={}, member_id={}",
                properties, kakao_account, kakao_client_id, email, member_id);

            userInfo.put("kakao_client_id", kakao_client_id);
            userInfo.put("email", email);
            userInfo.put("member_id", member_id);

        } catch (IOException e) {
            e.printStackTrace();
        }

        MemberSessionDto result = kakaoMemberDao.kakaoLogin(userInfo);

        log.debug("result={}", result);

        if (result == null) {
            kakaoMemberDao.kakaoInsert(userInfo);

            return kakaoMemberDao.kakaoLogin(userInfo);
        }

        return result;

    }

}
