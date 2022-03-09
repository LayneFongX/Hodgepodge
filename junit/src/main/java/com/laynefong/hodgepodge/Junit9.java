package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.List;

public class Junit9 {

    public static void main(String[] args) {
        String rule = "{\"entitySubIds\":\"130\",\"entityType\":1,\"condType\":2,\"entityId\":\"\",\"expr\":[[\"$dp130\"]]}";

        String SCHNEIDER_IHC_BRIDGE_PIDS = "ipnpcpyi,3ykdju7w,ranhcq9q,ymfuw251";
        String SCHNEIDER_IHC_RELAY_PIDS = "w6t2fpmj,wikacvlc,krblkhhp,asngagf5";
        String SCHNEIDER_IHC_WIRED_FREELOCATE_PIDS = "3xp88nut,nqndydy3,sxynblhb,k2vq8arq";
        String SCHNEIDER_IHC_WIRELESS_FREELOCATE_PIDS = "jdzcjivy,r3sly15m,9nb2ss4z,xomte1nk";
        String SCHNEIDER_IHC_TEMPERATURE_HUMIDITY_SENSOR_PIDS = "pfysvlak,cbt0srzp,hlqy1n3p,kd8jmh8a";
        String SCHNEIDER_IHC_WINDOW_DOOR_SENSOR_PIDS = "xriwwbmv,hzjtzkp1,ppfq1jjd,a9zimxft";
        String SCHNEIDER_IHC_DIMMER_PIDS = "xqdvs8eo,z2p8sjpt,a19i5pcm,apvrwa6j";
        String SCHNEIDER_IHC_FUGA_STIKKONTAKT_M_JORD_PIDS = "ylbvaygq,43owzxuu,9f0zikre,ye7met1l";
        String SCHNEIDER_IHC_COMBI_DIMMER_4_KEY_PIDS = "lt09mnnv,lrdyljwk,bu2eft1d,iwo8sjox";
        String SCHNEIDER_IHC_FUGA_UNI_ENH_LYSD_EMPER_UNI250_PIDS = "gxsee8xg,t9bx3qcw,rxceiyco,znyjztoj";
        String SCHNEIDER_IHC_COMBI_RELAY_4_KEY_PIDS = "qonaiytd,nzpe0nb3,go5vtfwg,eh4ml9pc";
        String SCHNEIDER_IHC_FUGA_REL_E_MODTAGER_ALLROUND_PIDS = "dwcwfp4u,fvb5t3pe,7fymh2pa,7nrkwwcb";
        String SCHNEIDER_PHILIPS_HUE_BRIDGE_PIDS = "sagy9jtq,0cmh10bz,4tk4m6ik,t84iiytc";
        String SCHNEIDER_PHILIPS_HUE_GU10_COLOR_TEMPERATURE_LIGHT_PIDS = "eekxsx7w,hdmkztcp,agkbvnfr,uqg0i1tw";
        String SCHNEIDER_PHILIPS_HUE_E14_COLOR_TEMPERATURE_LIGHT_PIDS = "b79jrsww,vc5ih3pd,r0d4dp1b,jdrx68zv";
        String SCHNEIDER_PHILIPS_HUE_E27_COLOR_TEMPERATURE_LIGHT_PIDS = "xvbcoosh,u8laahk0,hdic7j66,aqovz7ci";
        String SCHNEIDER_PHILIPS_HUE_GU10_DIMMABLE_LIGHT_WHITE_PIDS = "ra8d1cdh,pikba1vb,hxhtrsia,jlzepjop";
        String SCHNEIDER_PHILIPS_HUE_E14_DIMMABLE_LIGHT_WARM_PIDS = "28yje36,3umeielf,1xwahtg2,zelwgbki";
        String SCHNEIDER_PHILIPS_HUE_E27_DIMMABLE_LIGHT_WARM_PIDS = "cetti9qr,7xz3dpnp,dp5ebglg,citbpc0d";
        String SCHNEIDER_PHILIPS_HUE_E27_DIMMABLE_LIGHT_WHITE_PIDS = "vw8x4aqa,y7uup9kn,ti9uzif2,rmdf0kit";
        String SCHNEIDER_PHILIPS_HUE_GU10_RGBW_LIGHT_PIDS = "wfbmk563,juwn7gc3,n5hsld0a,jbbsot2m";
        String SCHNEIDER_PHILIPS_HUE_E27_RGBW_LIGHT_PIDS = "wr8vglmr,bgeafjay,qor0c7cw,uauqtmni";
        List<String> bridgeSubPids =
                Arrays.asList(SCHNEIDER_IHC_BRIDGE_PIDS, SCHNEIDER_IHC_RELAY_PIDS, SCHNEIDER_IHC_RELAY_PIDS,
                        SCHNEIDER_IHC_WIRED_FREELOCATE_PIDS,
                        SCHNEIDER_IHC_WIRELESS_FREELOCATE_PIDS, SCHNEIDER_IHC_TEMPERATURE_HUMIDITY_SENSOR_PIDS,
                        SCHNEIDER_IHC_WINDOW_DOOR_SENSOR_PIDS, SCHNEIDER_IHC_DIMMER_PIDS,
                        SCHNEIDER_IHC_FUGA_STIKKONTAKT_M_JORD_PIDS, SCHNEIDER_IHC_COMBI_DIMMER_4_KEY_PIDS,
                        SCHNEIDER_IHC_FUGA_UNI_ENH_LYSD_EMPER_UNI250_PIDS, SCHNEIDER_IHC_COMBI_RELAY_4_KEY_PIDS,
                        SCHNEIDER_IHC_FUGA_REL_E_MODTAGER_ALLROUND_PIDS, SCHNEIDER_PHILIPS_HUE_BRIDGE_PIDS,
                        SCHNEIDER_PHILIPS_HUE_GU10_COLOR_TEMPERATURE_LIGHT_PIDS, SCHNEIDER_PHILIPS_HUE_E14_COLOR_TEMPERATURE_LIGHT_PIDS,
                        SCHNEIDER_PHILIPS_HUE_E27_COLOR_TEMPERATURE_LIGHT_PIDS, SCHNEIDER_PHILIPS_HUE_GU10_DIMMABLE_LIGHT_WHITE_PIDS,
                        SCHNEIDER_PHILIPS_HUE_E14_DIMMABLE_LIGHT_WARM_PIDS, SCHNEIDER_PHILIPS_HUE_E27_DIMMABLE_LIGHT_WARM_PIDS,
                        SCHNEIDER_PHILIPS_HUE_E27_DIMMABLE_LIGHT_WHITE_PIDS, SCHNEIDER_PHILIPS_HUE_GU10_RGBW_LIGHT_PIDS,
                        SCHNEIDER_PHILIPS_HUE_E27_RGBW_LIGHT_PIDS);
        JSONArray ruleJsonArray = new JSONArray();
        for (String subType : bridgeSubPids) {
            List<String> pidList = Arrays.asList(subType.split(","));
            for (String pid : pidList) {
                JSONObject pidRule = JSONObject.parseObject(rule);
                pidRule.put("entityId", pid);
                ruleJsonArray.add(pidRule);
            }
        }
        System.out.println(ruleJsonArray);
    }

}
