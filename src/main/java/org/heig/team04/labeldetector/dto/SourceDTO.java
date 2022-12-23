package org.heig.team04.labeldetector.dto;


/**
 * SourceDTO is a DTO for the POST/PUT request to specify the source of the object.
 *
 * @author Ivan Vecerina, Yanik Lange
 * @version 1.0
 */
public class SourceDTO {
        private String uri = "";
        private byte[] content = new byte[0];
        private int maxLabels=2;
        private float minConfidence=0.8f;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
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
