    package com.example.Mi_Clinica.service;

    import com.example.Mi_Clinica.DTO.Request.OdontologoRequestDto;
    import com.example.Mi_Clinica.DTO.Response.OdontologoResponseDto;

    import java.util.List;

    public interface IOdontologoService {

        void eliminarOdontologoDto(Integer id);

        List<OdontologoResponseDto> listarTodos();

        OdontologoResponseDto buscarOdontologo(Integer id);

        OdontologoResponseDto guardarOdontologo(OdontologoRequestDto requestDto);

        OdontologoResponseDto actualizarOdontologo(Integer id, OdontologoRequestDto requestDto);



    }


