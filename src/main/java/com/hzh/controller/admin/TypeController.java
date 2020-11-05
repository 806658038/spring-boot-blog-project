package com.hzh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzh.pojo.Type;
import com.hzh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;


import java.util.List;

@Controller
@RequestMapping("/admin/type")
public class TypeController {

    @Autowired
    TypeService typeServiceImpl;

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
        List<Type> list = typeServiceImpl.getListType();
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "/admin/type";
    }

    // 进入 新增界面
    @GetMapping("/addPage")
    public String inputPage(){
        return "/admin/type-add";
    }

    @PostMapping("/add")
    public String addType(@Valid Type type, RedirectAttributes attributes){
        Type t = typeServiceImpl.queryTypeByName(type.getName());
        if(t!=null){
            attributes.addFlashAttribute("fail", "不能添加重复的分类");
            return "redirect:/admin/type/addPage";
        }
        int i = typeServiceImpl.insertType(type);
        if(i==1){
            attributes.addFlashAttribute("success","添加成功");
        }else{
            attributes.addFlashAttribute("fail","添加失败");
        }
        return "redirect:/admin/type/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteType(@PathVariable Long id,RedirectAttributes attributes){
        int i = typeServiceImpl.deleteType(id);
        if(i==1){
            attributes.addFlashAttribute("success", "删除成功");
        }else{
            attributes.addFlashAttribute("fail", "删除失败");
        }
        return "redirect:/admin/type/show";
    }

    @GetMapping("/{id}/updatePage")
    public String updTypePage(@PathVariable Long id, Model model){
        model.addAttribute("type",typeServiceImpl.queryOneType(id));
        return "/admin/type-edit";
    }

    @PostMapping("/update/{id}")
    public String updateType(@Valid Type type,@PathVariable Long id,RedirectAttributes attributes){
        Type t = typeServiceImpl.queryTypeByName(type.getName());
        if (t != null) {
            attributes.addFlashAttribute("fail", "名称相同 不用修改");
            return "redirect:/admin/type/"+id+"/updatePage";
        }
        int i = typeServiceImpl.updateType(type);
        if (i == 1 ) {
            attributes.addFlashAttribute("success", "修改成功");
        } else {
            attributes.addFlashAttribute("fail", "修改失败");
        }
        return "redirect:/admin/type/show";
    }

}
