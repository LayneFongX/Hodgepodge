package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONObject;
import com.laynefong.hodgepodge.domain.config.MultiLanguagePreConfigurationVO;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;

public class Junit10 {

    public static void main(String[] args) throws IOException {
        String configurationBody =
                "{\"languagesIdList\":[1,3,8,9,6,13,27,28,14],\"productsIdList\":[\"sdy3m5xz\",\"ajauadjx\",\"wdu8skfpbsgzptyt\",\"orey2coh\",\"z8ntnhnm\",\"e9zia2f8\",\"gapi6d7w\",\"efjqfavn\",\"hpdoxjcp\",\"r6ujohuyq1k4hf2h\",\"vphg6sfoeeidsqgs\",\"nhes5al5\",\"kajcq7c6\",\"bbldyq7g\",\"avcpmrz7\",\"aitkp0ex\",\"a9tu1kin\",\"adys8axm\",\"xxrehdbc\",\"pvvcfbsv\",\"s0unhvbv\",\"uzpsufgy\",\"bat2u3vq\",\"71jfvp7d\",\"jpzfjwkg\",\"ko8xkt82\",\"9hisayni\",\"szci7aem\",\"nxjcbami\",\"iimtywzy\",\"fhcsajfl\",\"bdeckiza\",\"i1ji3iet\",\"0splsq58d3vx0biu\",\"vi5nb7iz\",\"c9b5u4oa\",\"trlyllnn\",\"gnc9qkiu\",\"51mb7vds\",\"kp77j5i1\",\"etazuesn\",\"49xvo9gx\",\"ectjw1sf\",\"llqarm0v\",\"svdad5cs\",\"qailpekl\",\"e6rs3pan\",\"pca3q57r\",\"4lishogj\",\"j2j6jbfiqbftl22z\",\"gxsee8xg\",\"xriwwbmv\",\"ylbvaygq\",\"7nrkwwcb\",\"a9zimxft\",\"pqzcnghe\",\"kzgoakwg\",\"ewme1k3k\",\"xik4z550\",\"jzgi68tk\",\"8h3gz3hs\",\"pmt9jkfw\",\"zdn0skst\",\"wkx2kilz\",\"zrkycwqz\",\"t84iiytc\",\"if5yy6fx\",\"vw8x4aqa\",\"citbpc0d\",\"of1wzxhd\",\"umrjquhf6qtn53ff\",\"7asqoekk\",\"cymqlhkd\",\"qru9hvam\",\"j3m9tzcw\",\"0r1az3ld\",\"oa5axm0slgzmqydt\",\"7z2tz4gwimgdcpr1\",\"uucv6alo\",\"pf8qqyj2\",\"75asvzcb\",\"zuykfpfg7zyxvlox\",\"jdknwriz\",\"pvyuyxb4\",\"dtnjamiv\",\"wwaaewi6\",\"bnj74wzm\",\"inkdhnww\",\"cylmj2csguvmnilv\",\"hhyu70q5cxf66dmv\",\"uauqtmni\",\"ra8d1cdh\",\"wr8vglmr\",\"b79jrsww\",\"3a9plejp\",\"ohmz521a\",\"7ezjzyu5\",\"tngqq0ru\",\"dkzq0h8h\",\"offncznt\",\"zrntbjgc\",\"xkadbktg\",\"l23zumfy\",\"2jzyyive\",\"k2vq8arq\",\"ye7met1l\",\"xomte1nk\",\"3xp88nut\",\"3ykdju7w\",\"a6j0volhnvexvkyh\",\"hgl5a9gk\",\"k6loanw8\",\"uzy8a81t\",\"inagl8is\",\"nzrvu4dx\",\"5pqc6fo3\",\"ugev2swt\",\"neq9mqlu\",\"kpcpaswi\",\"dojfip4v\",\"fy012zc3l51doakx\",\"xunf9fgda5fai7br\",\"ntwonftx\",\"1of0llkc\",\"t2th30jy\",\"1ckj2uad\",\"trm7wzvc\",\"6onedv04\",\"ne1vfw5n\",\"rbuyxerm\",\"ac0pftt8lqrckgw1\",\"v5irm8oygvbqlq74\",\"w6t2fpmj\",\"jdzcjivy\",\"iwo8sjox\",\"znyjztoj\",\"wpjh5c75\",\"jrohjqsh\",\"6sih4vho\",\"at2v2p0i\",\"phaooefc\",\"nut6irzt\",\"azf35f7e\",\"65l0umq6\",\"kxcglqys\",\"3y2gwg8y\",\"xvbcoosh\",\"wfbmk563\",\"jdrx68zv\",\"uqg0i1tw\",\"wzghwmu2ukgqcyjl\",\"xxqjkd9zaxy6vhm2\",\"rkb8gvfcgmhswgxy\",\"mzbjvp0p\",\"xjfkuyoj\",\"cwehegoc\",\"zzgislz0\",\"ajnebdtw\",\"inm8cagml3k11tlk\",\"ougfufs2\",\"ag4mf1zn\",\"fsca3hnsebaesjql\",\"cudapyiu\",\"rzmepkhpzkwlrugw\",\"umbbx66nt7hxajtd\",\"jzvuuhdu\",\"y63sc4vgwnswwatw\",\"eofjjhtp\",\"whbsbxk8\",\"lv7cje21fkmmchra\",\"e0r5yqyuwnrkzarm\",\"kmiuligz\",\"jlzepjop\",\"zelwgbki\",\"h28yje36\",\"eekxsx7w\",\"wdxp8xwy\",\"oxmj9ntz\",\"g0ybafy2\",\"cl3ze8al\",\"hnxwoebx\",\"jrgfugev\",\"ooineszt\",\"mi9vd2ty\",\"0f6ru07v\",\"der4xuln\",\"asngagf5\",\"eh4ml9pc\",\"lt09mnnv\",\"dwcwfp4u\",\"ymfuw251\",\"macrl6bo\",\"dhqzhna7\",\"n3ajuigu\",\"hsrzalbf\",\"xmpk1gew\",\"ujtvovm4\",\"kmqnsqujolferjrw\",\"q0w68fxk\",\"2qjvor9q\",\"bhuvwtvl\",\"gd0m50pi\",\"pxrhdagx\",\"87cuyfibveaxiqyj\",\"ktdhbofe4oaxvpmi\",\"lgf01wci\",\"amnxgjdh\",\"gpxyi1cv\",\"1xr2houd\",\"vrgzwt8w\",\"xqdvs8eo\",\"pfysvlak\",\"qonaiytd\",\"apvrwa6j\",\"kd8jmh8a\",\"jr6oq3ccadyizaij\",\"8ji4jza5\",\"8ezhdnfi\",\"d2ncagua\",\"nrkyebgz\",\"1r3m4h1a\",\"nifq3juf\",\"oaum3dpp\",\"x7zpydy5\",\"rhavv31l\",\"sagy9jtq\",\"k6cbw48f\",\"cetti9qr\",\"aqovz7ci\",\"jbbsot2m\",\"bxvyp2cy95rfgt0a\",\"d19cvvhf\",\"abtjlazvfanwhjiu\",\"hyti2dnp\",\"iefpu3aj\",\"fent9g4p\",\"ojhetoeo\",\"ynsuc8wnzlo8zrqf\",\"kncwnvnt2gybfjkk\",\"abrzeuws\"]}";
        try {
            MultiLanguagePreConfigurationVO preConfigurationVO =
                    JSONObject.parseObject(configurationBody, MultiLanguagePreConfigurationVO.class);
        } catch (Exception e) {
            System.out.println(e);

        }
        String cacheValue = compress(configurationBody);
        cacheValue = compress(cacheValue);
        System.out.println(cacheValue);

        String unCompress = uncompress(cacheValue);
        unCompress = uncompress(unCompress);
        System.out.println(unCompress);
    }


    /**
     * 使用org.apache.commons.codec.binary.Base64压缩字符串
     *
     * @param str 要压缩的字符串
     * @return
     */
    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return Base64.encodeBase64String(str.getBytes());
    }

    /**
     * 使用org.apache.commons.codec.binary.Base64解压缩
     *
     * @param compressedStr 压缩字符串
     * @return
     */
    public static String uncompress(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }
        return new String(Base64.decodeBase64(compressedStr));
    }


}
