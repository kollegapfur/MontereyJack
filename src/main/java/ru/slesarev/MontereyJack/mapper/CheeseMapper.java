package ru.slesarev.MontereyJack.mapper;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.mapstruct.Mapper;
import ru.slesarev.MontereyJack.Dto.CheeseDto;
import ru.slesarev.MontereyJack.Entity.Cheese;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CheeseMapper {

    default Cheese toSource(CheeseDto cheeseDto){
        return new Cheese(cheeseDto.getName(), cheeseDto.getAge(), cheeseDto.getPrice());
    }

    default List<Cheese> toSource(List<CheeseDto> cheeseDtos){
        return cheeseDtos.stream().map(this::toSource).collect(Collectors.toList());
    }

    default CheeseDto toDto(Cheese cheese){
        if (cheese == null){
            return null;
        }
        CheeseDto cheeseDto = new CheeseDto();
        cheeseDto.setName(cheese.getName());
        cheeseDto.setAge(cheese.getAge());
        cheeseDto.setPrice(cheese.getPrice());

        return cheeseDto;
    }

    default List<CheeseDto> toDto(List<Cheese> cheeseList){
        return cheeseList.stream().map(this::toDto).collect(Collectors.toList());
    }

}
