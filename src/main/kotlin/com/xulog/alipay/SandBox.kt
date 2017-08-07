package com.xulog.alipay

import com.xulog.alipay.bean.misc.SignType

/**
 * Created by guofan on 2017/3/30.
 */
object SandBox : AliConfig(
        appId = "2016080300158457",
        signType = SignType.RSA2,
        privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC2qz3Uu8zB/4EQdiHz" +
                "S1QTkEyQf3ODxeABZWW0Swloh3U1AsMB5xkfsX0ctmRpLMy7KmnU/hyxGvU6Ao51A3lv9lmfPpXtPnu7Jrwek" +
                "Nj2Mvc9s2SRPOEzXA0AjQa8HsAFRTHBflJlC8FAvCyZDX35n1dieCF9QMjnOEliou+QRM/1yEMPX4vUt4ZIUS" +
                "N7bVAD/F8SCoIogHd4nBSORfXuiOxLezR9YZerP2nOLWpMlu0EQ9CQfx11XKj9qXE9cSVrOuBxEI59OY8InuW" +
                "OPJqlDXubOAhAe3wj0yIh3KTf5xvakCCpspRiXTCWVynC4lGC7vUQn1h7vwBJLGhYp4fvAgMBAAECggEBAKuR" +
                "7VJJTgmMpTnD6yFryD1T1EYIH4ROL5bAGSWdMOoICLNQs76uw6878sosGeWbWXQWYy7Vujcync/tpMYNDFt35" +
                "Deo/gLGJErlF94RqGad+hDu1O+BYffuZpv02bWJoEuS8SfPMS6NOudFlxHuVTtHSTlIohPV7J0YBf1qEl7KW1" +
                "VoIV/acPgVX3WoTO/pMZotoDXvZNy5VLjgjsCaYiZZWMfSQZG/ljHeV5+pHX0umPV7HnZxJGVMadudqyeFwgm" +
                "VJIYWofNxPKUPRJDHUkU6+P0rmjN/e4tJM6kodIAHi4bowKfoF1ZtJjADyOoVmeYVqd67wMTnkBTfb9pkbPEC" +
                "gYEA69K/vNiWCqIxDsSs/skorimnaakiD2J5eubWGYrwtLiwZ44GfFgH+9TTQMHWhDIp5+03rtBVDXRtF85iK" +
                "pQba/L9fyBd0C5AbFa7jUBN39BdbdZUPVKPlR64Q25G2TD24/XvC3Y/7/PxZDhjBZe4FFNbBMNy84yTKFljme" +
                "io9bkCgYEAxkxAA1NjdoxmxQ50BLjUjFiB2zb+p0h5V26L5RuGUW5b188So4axMv6TA8WqG/BQCkre8zGbl6T" +
                "ODM++F5Lk1m5EGbcG4J5z6vgB1uRtI4p1fu1y7WoXJGOt0wJnXxfJqcYcNVV7I0zPpohw4QHizUz9xzwpNv0E" +
                "2qJLQDmnPucCgYAzqxY1cyLUq5qiCLd6j2ppVlkGznbW9hV1txPbvtRkVD7aQYTsQ4FkTMoIWy/8AN2rog7cL" +
                "2sjKoooeCCMAa1MZo3iB55mWWvmEM7A8QDe89E0mowUb31kB7oJlCTTu2Wsppq8YSk7ErCl67DObeJ4GZdMoO" +
                "ITuI4ZfhRTiWVTiQKBgQC4rplLv6U56sdoLe7V2wJx95hxfwWq7O++A0IdTgZJ3vuAweW9ERwFv11SvQySWr1" +
                "g6Jw+aWxczmvtOKn1MKUaJLYeMP0hw9F3/CVSa4pN+I03epR4Ez+cb42boXh+8xupl837yjIWDVqz4g0nMmd2" +
                "B4evwBMumHWl3lMxbMCb7wKBgQCsN9AIciWHn20/+W4fKHYz1LzvHnkPyNtWpjqTMROKvDV23NhZNP3awSY9H" +
                "4CwKxQe43ouC4tYVTylv+0QOXUgJIpxSq9CYIs4nXZYKsbyj3FWb3zox9hJmjkYPJ4A9Gx9hY1Cnz6aUoMJnQ" +
                "X1/Ia1Q1eSejsHt2yoTSPlvEpZOw==",
        publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxYmJXuonTn6JU2v5qkZZ49Ufr/L" +
                "55ZUn/mPia7WwEnTDflbPQpzKUNy5sg5rXbMPpSF7hZf3kOem5bcCJwz7slwd44SqvvJDsc/cfeqDIGPrXlQu" +
                "xQM7L0vkkQ3RKPTudjU58sfC1WC/yPZKXHEmMGll7vImi5yIUPbq4Vbu58tMhiKisUO2tWvoW6M+XS56u9287" +
                "pJAPIyS4I3C19rzYCfZ7yOMAEWp4sltnlwiJy1WcMJAxtODjbcCGideBLIkwJbkigliby+8s1jf9me56hnMd7" +
                "R4Hf64YjeCjORnPcawQJNByeVKP4a8Rr8N6ThIpNrlF6J8ZNKgFGbRcjRNAwIDAQAB",
        gateWay = "https://openapi.alipaydev.com/gateway.do",
        notifyUrl = ""
) {

    fun initNotifyUrl(url: String): AliConfig {
        this.notifyUrl = url
        return this
    }
}