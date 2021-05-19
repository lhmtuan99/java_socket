/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author OS
 */
public class RSA {
    //text
    //tạo 2 nguyên lớn ngẫu nhiên
    public static Random rand1 = new Random(System.currentTimeMillis());
    public static Random rand2 = new Random(System.currentTimeMillis()*10);
    //tạo 2 số nguyên tố lớn từ 2 số vừa lấy
    public static BigInteger p = BigInteger.probablePrime(32, rand1);
    public static BigInteger q = BigInteger.probablePrime(32, rand2);
    //public key
    public static int pubkey = 2;
    //tính tích của 2 số n=pq
    public static BigInteger n = p.multiply(q);
    //tính phi = (p-1)(q-1)
    public static BigInteger p_1 = p.subtract(new BigInteger("1"));
    public static BigInteger q_1 = q.subtract(new BigInteger("1"));
    public static BigInteger phi = p_1.multiply(q_1);
    //Tìm e trong khoảng(1,phi) sao cho UCLN(e,phi)=1
    //Tìm d sao cho e.d = 1 mod phi
    public static BigInteger getPubKey()
    {
        BigInteger GCD;
        while(true)
        {
            GCD = phi.gcd(new BigInteger(""+pubkey));
            if(GCD.equals(BigInteger.ONE))
            {
                break;
            }
            pubkey++;
        }
        return new BigInteger(""+pubkey);
    }
    
    public static BigInteger getPrvKey()
    {
        return getPubKey().modInverse(phi);
    }
    
    public static BigInteger getN()
    {
        return n;
    }
    
    //Cặp khóa công khai và bí mật
    public static String PublicKey()
    {
        return getN().toString()+";"+getPubKey().toString();
    }
    public static String PrivateKey()
    {
        return getN().toString()+";"+getPrvKey().toString();
    }
    
    //giải mã và mã hóa
    public static String Encrypt(String msg, String key)
    {
        String cipherText = "";
        String keyPublic[] = key.split(";");
        BigInteger N = new BigInteger(keyPublic[0]);
        BigInteger e = new BigInteger(keyPublic[1]);
        //System.out.println("n và e: "+N+"; "+e);
        byte[] bytes = msg.getBytes();
        for(int i=0;i<msg.length();i++)
        {
            int asciiVal=bytes[i];
            //System.out.println("mã asci:"+asciiVal);
            BigInteger val = new BigInteger(""+asciiVal);
            BigInteger cipherVal = val.modPow(e,N);
            
            cipherText += cipherVal+";"; 
        }
        return cipherText;
    }
    public static String Decrypt(String cypherText, String key)
    {
        String plainText="";
        String []cypher = cypherText.split(";");
        for(int i=0;i<cypher.length;i++)
        {
            BigInteger plainVal = new BigInteger(""+cypher[i]).modPow(getPrvKey(),n);
            int i_plainVal = plainVal.intValue();
            plainText += (""+(char)i_plainVal);
        }
        return plainText;
    }
    public static void main(String[] args)
    {
        System.out.println(p + " " + q);
        System.out.println("public key: "+PublicKey());
        System.out.println("private key:"+PrivateKey());
        String cypherText = Encrypt("Tuan",PublicKey());
//        for(int i=0;i<cypherText.length;i++)
//        {
//            System.out.println("cypher["+i+"]="+cypherText[i]);
//        }
        System.out.println("Mã hóa:" +cypherText);
        System.out.println("Giải mã:" +Decrypt(cypherText,PrivateKey()));
    }
}
