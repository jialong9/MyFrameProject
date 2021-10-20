package com.vcyber.myframe.bean;

import com.vcyber.myframe.base.BaseClassResultBean;

import java.util.List;

/**
 * description ：
 * author : zjl
 * date : 9/6/21
 */
public class XingTuBean extends BaseClassResultBean<XingTuBean.XingTuData> {

    public static class XingTuData {
        private List<Authors> authors;

        public List<Authors> getAuthors() {
            return authors;
        }

        public void setAuthors(List<Authors> authors) {
            this.authors = authors;
        }

        public static class Authors {
            private String nick_name;//昵称
            private String follower;//粉丝数
            private String expected_cpm;// 预计cpm
            private String expected_play_num;// 预计播放量
            private String fans_increment_rate_within_30d;// 粉丝增加率
            private String star_item_count_within_30d;// 完播率
            private String interact_rate_within_30d;// 互动率
            private String fans_increment_within_30d;// 粉丝增长量
            private List<PriceInfo> price_info;
            private long median_game_item_component_click_90_days;
            private long median_game_item_ctr_90_days;
            private long median_game_item_cpc_90_days;
            private long game_item_count_90_days;

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getFollower() {
                return follower;
            }

            public void setFollower(String follower) {
                this.follower = follower;
            }

            public String getExpected_cpm() {
                return expected_cpm;
            }

            public void setExpected_cpm(String expected_cpm) {
                this.expected_cpm = expected_cpm;
            }

            public String getExpected_play_num() {
                return expected_play_num;
            }

            public void setExpected_play_num(String expected_play_num) {
                this.expected_play_num = expected_play_num;
            }

            public String getFans_increment_rate_within_30d() {
                return fans_increment_rate_within_30d;
            }

            public void setFans_increment_rate_within_30d(String fans_increment_rate_within_30d) {
                this.fans_increment_rate_within_30d = fans_increment_rate_within_30d;
            }

            public String getStar_item_count_within_30d() {
                return star_item_count_within_30d;
            }

            public void setStar_item_count_within_30d(String star_item_count_within_30d) {
                this.star_item_count_within_30d = star_item_count_within_30d;
            }

            public String getInteract_rate_within_30d() {
                return interact_rate_within_30d;
            }

            public void setInteract_rate_within_30d(String interact_rate_within_30d) {
                this.interact_rate_within_30d = interact_rate_within_30d;
            }

            public String getFans_increment_within_30d() {
                return fans_increment_within_30d;
            }

            public void setFans_increment_within_30d(String fans_increment_within_30d) {
                this.fans_increment_within_30d = fans_increment_within_30d;
            }

            public List<PriceInfo> getPrice_info() {
                return price_info;
            }

            public void setPrice_info(List<PriceInfo> price_info) {
                this.price_info = price_info;
            }

            public long getMedian_game_item_component_click_90_days() {
                return median_game_item_component_click_90_days;
            }

            public void setMedian_game_item_component_click_90_days(long median_game_item_component_click_90_days) {
                this.median_game_item_component_click_90_days = median_game_item_component_click_90_days;
            }

            public long getMedian_game_item_ctr_90_days() {
                return median_game_item_ctr_90_days;
            }

            public void setMedian_game_item_ctr_90_days(long median_game_item_ctr_90_days) {
                this.median_game_item_ctr_90_days = median_game_item_ctr_90_days;
            }

            public long getMedian_game_item_cpc_90_days() {
                return median_game_item_cpc_90_days;
            }

            public void setMedian_game_item_cpc_90_days(long median_game_item_cpc_90_days) {
                this.median_game_item_cpc_90_days = median_game_item_cpc_90_days;
            }

            public long getGame_item_count_90_days() {
                return game_item_count_90_days;
            }

            public void setGame_item_count_90_days(long game_item_count_90_days) {
                this.game_item_count_90_days = game_item_count_90_days;
            }

            private static class PriceInfo {
                private String price;//价格
                private String desc;//描述
            }
        }
    }
} 