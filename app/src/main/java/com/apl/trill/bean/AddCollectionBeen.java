package com.apl.trill.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public class AddCollectionBeen {

    private String msg;

    private String status_code;

    private String has_more;

    private String cursor;

    private List<Mc> mc_list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getHas_more() {
        return has_more;
    }

    public void setHas_more(String has_more) {
        this.has_more = has_more;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public List<Mc> getMc_list() {
        return mc_list;
    }

    public void setMc_list(List<Mc> mc_list) {
        this.mc_list = mc_list;
    }

    public static class Mc{

        private String mc_name;

        private String name;

        private Cover cover;

        private String mc_id;

        private AwemeCover aweme_cover;

        private String id_str;

        private int id;

        public String getMc_name() {
            return mc_name;
        }

        public void setMc_name(String mc_name) {
            this.mc_name = mc_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Cover getCover() {
            return cover;
        }

        public void setCover(Cover cover) {
            this.cover = cover;
        }

        public String getMc_id() {
            return mc_id;
        }

        public void setMc_id(String mc_id) {
            this.mc_id = mc_id;
        }

        public AwemeCover getAweme_cover() {
            return aweme_cover;
        }

        public void setAweme_cover(AwemeCover aweme_cover) {
            this.aweme_cover = aweme_cover;
        }

        public String getId_str() {
            return id_str;
        }

        public void setId_str(String id_str) {
            this.id_str = id_str;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class Cover{

        private List<String> url_list;

        private String uri;

        public List<String> getUrl_list() {
            return url_list;
        }

        public void setUrl_list(List<String> url_list) {
            this.url_list = url_list;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }

    public static class AwemeCover{

        private List<String> url_list;

        private String uri;

        public List<String> getUrl_list() {
            return url_list;
        }

        public void setUrl_list(List<String> url_list) {
            this.url_list = url_list;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }

}
