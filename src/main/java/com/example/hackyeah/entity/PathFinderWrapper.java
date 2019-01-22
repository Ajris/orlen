package com.example.hackyeah.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PathFinderWrapper {
    private Crossroad start;
    private Crossroad end;
    @Valid
    @NonNull
    private Vehicle vehicle;
}
