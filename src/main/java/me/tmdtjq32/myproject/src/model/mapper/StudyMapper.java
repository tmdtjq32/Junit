package me.tmdtjq32.myproject.src.model.mapper;

import me.tmdtjq32.myproject.src.model.DTO.StudyReqDTO;
import me.tmdtjq32.myproject.src.model.DTO.StudyResDTO;
import me.tmdtjq32.myproject.src.model.entity.Study;
import me.tmdtjq32.myproject.src.model.entity.Member;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
imports = {
        Member.class
})
public interface StudyMapper {
    StudyMapper INSTANCE = Mappers.getMapper(StudyMapper.class);

    @Mapping(target = "limit", source = "chapter")
    StudyResDTO toStudyResDTO(Study study);

    @Mappings({
            @Mapping(target = "owner", expression = "java(Member.builder().idx(dto.getOwner()).build())"),
            @Mapping(target = "idx", ignore = true),
            @Mapping(target = "chapter", source = "limit")
    })
    Study toStudy(StudyReqDTO dto);

}
