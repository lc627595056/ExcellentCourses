package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-03
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    //删除章节的方法
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean result = eduChapterService.removeChapterId(chapterId);
        if(result) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    //修改章节的方法
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        boolean result = eduChapterService.updateById(eduChapter);
        if(result) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    //根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.ok().data("eduChapter",eduChapter);
    }

    // 添加章节的方法
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        boolean save = eduChapterService.save(eduChapter);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据id查询查询章节和小节
    @GetMapping("getChapterVideoList/{courseId}")
    public R getChapterVideoListCourseId(@PathVariable String courseId) {
        List<EduChapterDto> list = eduChapterService.getChapterVideoListByCourseId(courseId);
        return R.ok().data("items",list);
    }
}

