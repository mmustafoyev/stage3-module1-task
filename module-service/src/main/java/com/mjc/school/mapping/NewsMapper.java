//package com.mjc.school.mapping;
//
//import com.mjc.school.dto.NewsDto;
//import com.mjc.school.repository.model.AuthorModel;
//import org.mapstruct.Mapper;
//import com.mjc.school.repository.model.NewsModel;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
//public interface NewsMapper {
//    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "title", target = "title")
//    @Mapping(source = "content", target = "content")
//    @Mapping(source = "createdDate", target = "createDate")
//    @Mapping(source = "lastUpdatedDate", target = "lastUpdateDate")
//    @Mapping(source = "authorId", target = "authorId")
//    @Mapping(source = "name", target = "authorName")
//    NewsDto newsToNewsDTO(NewsModel news, AuthorModel author);
//
//}
