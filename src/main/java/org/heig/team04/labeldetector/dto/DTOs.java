package org.heig.team04.labeldetector.dto;

public class DTOs {

    public static class UriDTO {
        private String uri;
        private int maxLabels;
        private float minConfidence;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public int getMaxLabels() {
            return maxLabels;
        }

        public float getMinConfidence() {
            return minConfidence;
        }

        public void setMaxLabels(int maxLabels) {
            this.maxLabels = maxLabels;
        }

        public void setMinConfidence(float minConfidence) {
            this.minConfidence = minConfidence;
        }
    }

    public static class ContentDTO {
        private byte[] content;
        private int maxLabels;
        private float minConfidence;

        public ContentDTO(byte[] content, int maxLabels, float minConfidence) {
            this.content = content;
            this.maxLabels = maxLabels;
            this.minConfidence = minConfidence;
        }

        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }

        public int getMaxLabels() {
            return maxLabels;
        }

        public void setMaxLabels(int maxLabels) {
            this.maxLabels = maxLabels;
        }

        public float getMinConfidence() {
            return minConfidence;
        }

        public void setMinConfidence(float minConfidence) {
            this.minConfidence = minConfidence;
        }
    }
}
