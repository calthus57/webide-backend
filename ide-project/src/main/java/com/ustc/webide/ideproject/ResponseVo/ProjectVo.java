package com.ustc.webide.ideproject.ResponseVo;

import lombok.Data;

import java.util.List;

@Data
public class ProjectVo {
    private List<ProjectContent> children;
    private Integer project_id;
}
