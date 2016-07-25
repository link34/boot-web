package jp.co.sekainet.traning.type;

import static jp.co.sekainet.traning.type.Prefecture.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.val;

public enum Region {

    UNSELECTED("未選択", Prefecture.UNSELECTED),
    HOKKAIDO_TOHOKU("北海道・東北", HOKKAIDO, AOMORI, IWATE, MIYAGI, AKITA, YAMAGATA, FUKUSHIMA),
    KANTO("関東", IBARAKI, TOCHIGI, GUNMA, SAITAMA, CHIBA, TOKYO, KANAGAWA),
    CHUBU("中部", NIIGATA, TOYAMA, ISHIKAWA, FUKUI, YAMANASHI, NAGANO, GIFU, SHIZUOKA, AICHI),
    KINKI("近畿", MIE, SHIGA, KYOTO, OSAKA, HYOGO, NARA, WAKAYAMA),
    CHUGOKU("中国", TOTTORI, SHIMANE, OKAYAMA, HIROSHIMA, YAMAGUCHI),
    SHIKOKU("四国", TOKUSHIMA, KAGAWA, EHIME, KOCHI),
    KYUSHU_OKINAWA("九州・沖縄", FUKUOKA, SAGA, NAGASAKI, KUMAMOTO, MIYAZAKI, KAGOSHIMA, OKINAWA),
    ;

    public final String name;

    public final List<Prefecture> prefectures;

    private Region(String name, Prefecture... prefectures) {
        this.name = name;
        val listedPrefectures = Arrays.asList(prefectures);
        this.prefectures = Collections.unmodifiableList(listedPrefectures);
    }
}
