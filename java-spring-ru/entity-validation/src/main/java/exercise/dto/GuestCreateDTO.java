package exercise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    private String phoneNumber;

    @Pattern(regexp = "^\\d{4}$")
    private String clubCard;

    @Future
    private Date cardValidUntil;
}
// END
