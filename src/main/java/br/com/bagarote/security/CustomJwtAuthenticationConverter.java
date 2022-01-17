package br.com.bagarote.security;
import com.nimbusds.jose.shaded.json.JSONArray;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken>
{
    @Override
    public AbstractAuthenticationToken convert(final Jwt jwt)
    {
        return new JwtAuthenticationToken(jwt, extractResourceRoles(jwt));
    }

    private static Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt)
    {
        Collection<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        JSONArray jsonArray = jwt.getClaim("authorities");
        for(Object aut:jsonArray){
            System.out.println("lista : "+aut.toString());
        }
        jsonArray.forEach(json -> grantedAuthorities.add(new SimpleGrantedAuthority(json.toString())));

        return grantedAuthorities;
    }
}
