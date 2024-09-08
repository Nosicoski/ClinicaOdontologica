package com.example.Arevalo_Saibene_Nosicoski.DTO.Response;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.DomicilioResponseDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DomicilioResponseDto {
    private Integer id;//*
    private String calle;//*
    private int numero;//*
    private String localidad;//*
    private String provincia;//*

}
