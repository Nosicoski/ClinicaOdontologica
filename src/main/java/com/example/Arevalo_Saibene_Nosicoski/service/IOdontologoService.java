    package com.example.Arevalo_Saibene_Nosicoski.service;

    import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.OdontologoRequestDto;
    import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.OdontologoResponseDto;
    import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
    import com.example.Arevalo_Saibene_Nosicoski.repository.IOdontologoRepository;

    import java.util.List;



    import java.util.Optional;

    public interface IOdontologoService {

        void eliminarOdontologoDto(Integer id);

        List<OdontologoResponseDto> listarTodos();

        OdontologoResponseDto buscarOdontologo(Integer id);

        OdontologoResponseDto guardarOdontologo(OdontologoRequestDto requestDto);

        OdontologoResponseDto actualizarOdontologo(Integer id, OdontologoRequestDto requestDto);



    }


