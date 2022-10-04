package explore.mapper;

import explore.model.Compilation;
import explore.model.Event;
import explore.model.dto.CompilationDto;
import explore.model.dto.CompilationShortDto;

import java.util.stream.Collectors;

public class CompilationMapper {
    public static CompilationDto toCompilationDto(Compilation compilation) {
        return new CompilationDto(compilation.getId(),
                compilation.getEvents(),
                compilation.getPinned(),
                compilation.getTitle());
    }

    public static CompilationShortDto toCompilationShortDto(Compilation compilation) {
        return new CompilationShortDto(compilation.getId(),
                compilation.getEvents().stream()
                        .map(Event::getId)
                        .collect(Collectors.toList()),
                compilation.getPinned(),
                compilation.getTitle());
    }

    public static Compilation toCompilation(CompilationDto compilationDto) {
        return new Compilation(compilationDto.getId(),
                compilationDto.getEvents(),
                compilationDto.getPinned(),
                compilationDto.getTitle());
    }
}
