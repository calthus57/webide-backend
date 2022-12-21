package com.ustc.webide.ideproject.ResponseVo;

import com.ustc.webide.ideproject.entity.FileTableEntity;
import lombok.Data;

import java.util.List;
@Data
public class PathToFolder {
    FileTableEntity fileTableEntity;
    List<Integer> path;

    public PathToFolder(List<Integer> path, FileTableEntity fileTableEntity) {
    }
}
