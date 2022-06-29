package com.vcyber.myframe.bean;

import java.io.Serializable;

/**
 * description ：
 * author : zjl
 * date : 7/27/21
 */
public class TestJsonBean implements Serializable {
    private String status;
    private ResponseJson response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResponseJson getResponse() {
        return response;
    }

    public void setResponse(ResponseJson response) {
        this.response = response;
    }

    public static class ResponseJson {
        private BodyJson body;

        public BodyJson getBody() {
            return body;
        }

        public void setBody(BodyJson body) {
            this.body = body;
        }

        public static class BodyJson {
            private String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
} 