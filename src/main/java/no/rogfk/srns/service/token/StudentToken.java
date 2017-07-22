package no.rogfk.srns.service.token;

import lombok.Data;
import lombok.EqualsAndHashCode;
import no.rogfk.jwt.claims.DefaultClaim;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentToken extends DefaultClaim {
    private String id;
}
