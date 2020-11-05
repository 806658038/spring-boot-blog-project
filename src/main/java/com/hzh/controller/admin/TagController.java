package com.hzh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzh.pojo.Tag;
import com.hzh.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/tag")
public class TagController {

    @Autowired
    TagService tagServiceImpl;

    /**
     * 分页
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/show")
    public String types(@RequestParam(defaultValue = "0",value = "pageNum") Integer pageNum,Model model){
        int pageSize=5;
        PageHelper.startPage(pageNum,pageSize);
        List<Tag> list = tagServiceImpl.getListTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(list);

        model.addAttribute("pageInfo",pageInfo);
        return "/admin/tag";
    }

    // 进入 新增界面
    @GetMapping("/addPage")
    public String inputPage(){
        return "/admin/tag-add";
    }

    @PostMapping("/add")
    public String addType(@Valid Tag tag, RedirectAttributes attributes){
        Tag t = tagServiceImpl.queryTagByName(tag.getName());

        if(t!=null){
            attributes.addFlashAttribute("fail", "不能添加重复的标签");
            return "redirect:/admin/tag/addPage";
        }
        int i = tagServiceImpl.insertTag(tag);
        if(i==1){
            attributes.addFlashAttribute("success","添加成功");
        }else{
            attributes.addFlashAttribute("fail","添加失败");
        }
        return "redirect:/admin/tag/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteType(@PathVariable Long id,RedirectAttributes attributes){
        int i = tagServiceImpl.deleteTag(id);
        if(i==1){
            attributes.addFlashAttribute("success", "删除成功");
        }else{
            attributes.addFlashAttribute("fail", "删除失败");
        }
        return "redirect:/admin/tag/show";
    }

    @GetMapping("/{id}/updatePage")
    public String updTypePage(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagServiceImpl.queryOneTag(id));
        return "/admin/tag-edit";
    }

    @PostMapping("/update/{id}")
    public String updateType(@Valid Tag tag,@PathVariable Long id,RedirectAttributes attributes){
        Tag t = tagServiceImpl.queryTagByName(tag.getName());
        if (t != null) {
            attributes.addFlashAttribute("fail", "名称相同 不用修改");
            return "redirect:/admin/tag/"+id+"/updatePage";
        }
        int i = tagServiceImpl.updateTag(tag);
        if (i == 1 ) {
            attributes.addFlashAttribute("success", "修改成功");
        } else {
            attributes.addFlashAttribute("fail", "修改失败");
        }
        return "redirect:/admin/tag/show";
    }

}
