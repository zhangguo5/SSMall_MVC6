package com.zhangguo.ssmall.controllers;

import java.io.File;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.zhangguo.ssmall.entities.Goods;
import com.zhangguo.ssmall.services.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Resource
	GoodsService goodsService;
	
	/*
	 * 产品列表与分页Action
	 */
	@RequestMapping("/list")
	public String list(Model model,@RequestParam(required=false,defaultValue="1") int pageNO){
		int size=5;
		model.addAttribute("size",size);
		model.addAttribute("pageNO",pageNO);
		model.addAttribute("count",goodsService.getGoodsCount());
		model.addAttribute("goods", goodsService.getGoodsPager(pageNO, size));
		return "goods/list";
	}
	
	/*
	 * 删除单个产品对象Action
	 */
	@RequestMapping("/delete/{id}")
	public String delete(Model model,@PathVariable int id,@RequestParam(required=false,defaultValue="1") int pageNO,RedirectAttributes redirectAttributes){
		if(goodsService.delete(id)>0)
		{
			redirectAttributes.addFlashAttribute("message", "删除成功！");
		}else{
			redirectAttributes.addFlashAttribute("message", "删除失败！");
		}
		return "redirect:/goods/list?pageNO="+pageNO;
	}
	
	/*
	 * 删除多个产品对象Action
	 */
	@RequestMapping("/deletes")
	public String deletes(Model model,@RequestParam int[] id,@RequestParam(required=false,defaultValue="1") int pageNO,RedirectAttributes redirectAttributes){
		//执行删除
		int rows=goodsService.deletes(id);
		if(rows>0)
		{
			redirectAttributes.addFlashAttribute("message", "删除"+rows+"行记录成功！");
		}else{
			redirectAttributes.addFlashAttribute("message", "删除失败！");
		}
		return "redirect:/goods/list?pageNO="+pageNO;
	}
	
	/*
	 * 添加商品
	 */
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("entity", new Goods());
		return "goods/add";
	}
	
	/*
	 * 添加商品保存
	 */
	@RequestMapping("/addSave")
	public String addSave(Model model,@ModelAttribute("entity") @Valid Goods entity,BindingResult bindingResult){
		//如果模型中存在错误
		if(!bindingResult.hasErrors()){
			if(goodsService.insert(entity)>0)
			{
				return "redirect:/goods/list";	
			}
		}
		model.addAttribute("entity", entity);
		return "goods/add";
	}
	
	/*
	 * 编辑商品
	 */
	@RequestMapping("/edit/{id}")
	public String edit(Model model,@PathVariable int id){
		model.addAttribute("entity", goodsService.getGoodsById(id));
		return "goods/edit";
	}
	
	/*
	 * 编辑商品保存
	 */
	@RequestMapping("/editSave")
	public String editSave(Model model,@ModelAttribute("entity") @Valid Goods entity,BindingResult bindingResult){
		//如果模型中存在错误
		if(!bindingResult.hasErrors()){
			if(goodsService.update(entity)>0)
			{
				return "redirect:list";	
			}
		}
		model.addAttribute("entity", entity);
		return "/goods/edit";
	}
	
	/**
	 * 上传图片
	 */
	@RequestMapping("/upPicture/{id}")
	public String upPicture(Model model,@PathVariable int id){
		model.addAttribute("entity", goodsService.getGoodsById(id));
		return "goods/upfile";
	}
	
	/*
	 * 上传图片保存
	 */
	@RequestMapping("/upPictureSave/{id}")
	public String upPictureSave(Model model,@PathVariable int id,MultipartFile picFile,HttpServletRequest request){
		Goods entity=goodsService.getGoodsById(id);
		//如果选择了文件
		if(picFile!=null){ 
			//如果文件大小不为0
			if(picFile.getSize()>0){
				//获得上传位置
				String path=request.getServletContext().getRealPath("/images");
				//生成文件名
				String filename=UUID.randomUUID().toString()+picFile.getOriginalFilename().substring(picFile.getOriginalFilename().lastIndexOf("."));
				File tempFile=new File(path, filename);
				try {
					//保存文件
					picFile.transferTo(tempFile);
					//更新数据
					entity.setPicture(filename);
					goodsService.update(entity);
					//转向列表页
					return "redirect:/goods/list";	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("entity", entity);
		return "goods/upfile";
	}
}
