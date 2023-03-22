package com.acorn.webappboard.lib;

import org.junit.jupiter.api.Test;

class AESEncryptionTest {
    //**테스트를 할때마다 대칭키를 새롭게 생성한다. => 서버 끄고 재시작하면 대칭키를 새롭게 만든다.
    // => 서버를 재시작하면 재시작 전에 생성한 암호된 쿠키값을 복호화 할 수 없다.

    //admin123 (x) => 서버를 재시작할 때마다 랜덤 생성
    //서버를 재시작해도 대칭키를 잃어버리지 않도록 대칭키를 파일로 저장한다.(파일이 있으면 새로생성하지 않음)
    @Test
    void encryptValue() throws Exception {
        String encryptVal=AESEncryption.encryptValue("user01");
        System.out.println(encryptVal);
        String decryptVal=AESEncryption.decryptValue(encryptVal);
        System.out.println(decryptVal);
    }
}