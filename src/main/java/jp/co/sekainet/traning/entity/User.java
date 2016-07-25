package jp.co.sekainet.traning.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import jp.co.sekainet.traning.type.Prefecture;
import lombok.Data;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Data
public class User implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue @Min(0)
    private long id;

    @NotEmpty @Length(min=6, max=50)
    @Pattern(regexp="[a-zA-Z0-9\\.\\-_]*", message="ログインＩＤは半角英数字と。-_のみ使用できます")
    private String loginId;
    
    @NotEmpty @Length(min=8, max=100)
    private String password;

    @NotEmpty @Length(max=50)
    private String name;
    
    @Length(max=100)
    @Pattern(regexp="[ぁ-ゞー]*", message="よみがなはすべて平仮名で入力して下さい")
    private String phonetic;
    
    @Pattern(regexp="^$|\\d{3}-\\d{4}", message="郵便番号はハイフンを含めて7桁の数値で入力して下さい")
    private String postalCode;
    
    @Range(min=0, max=47, message="都道府県の選択値が不正です。")
    private int prefectureId;
    
    @Length(max=50)
    private String city;
    
    @Length(max=50)
    private String address;
    
    @Pattern(regexp="^$|0\\d{1,4}-\\d{1,4}-\\d{3,4}", message="電話番号ははハイフンを含めて数値で入力して下さい")
    private String telephone;
    
    @Email
    private String email;
    
    @Length(max=500)
    private String note;
    
    private boolean admin;

//    public void setId(long id) throws IllegalArgumentException {
//        if (id <= 0L) throw new IllegalArgumentException("Id must be positive value.");
//        this.id = id;
//    }
    
    public Prefecture getPrefecture(){
        return Prefecture.values()[prefectureId];
    }
}
