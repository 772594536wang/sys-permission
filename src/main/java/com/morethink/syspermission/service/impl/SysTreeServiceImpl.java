package com.morethink.syspermission.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.morethink.syspermission.dao.dept.SysDeptMapper;
import com.morethink.syspermission.dto.DeptLevelDTO;
import com.morethink.syspermission.entity.dept.SysDept;
import com.morethink.syspermission.service.SysTreeService;
import com.morethink.syspermission.util.LevelUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 系统中tree对象的接口实现类
 *
 * @author wangpf
 */
@Service
public class SysTreeServiceImpl implements SysTreeService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<DeptLevelDTO> getDeptTree() {
        List<SysDept> deptLists = sysDeptMapper.selectAllDept();
        List<DeptLevelDTO> dtoList = new ArrayList<>();
        for (SysDept dept : deptLists) {
            DeptLevelDTO dto = DeptLevelDTO.adapt(dept);
            dtoList.add(dto);
        }

        return deptListToTree(dtoList);
    }

    private List<DeptLevelDTO> deptListToTree(List<DeptLevelDTO> deptLevelList) {
        if (CollectionUtils.isEmpty(deptLevelList)) {
            return deptLevelList;
        }
        // 以level为key，部门list为value
        Multimap<String, DeptLevelDTO> levelDeptMap = ArrayListMultimap.create();
        // 获取一级部门
        List<DeptLevelDTO> rootList = new ArrayList<>();
        for (DeptLevelDTO dto : deptLevelList) {
            levelDeptMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }

        // 按照seq从小到大排序
        rootList.sort(new Comparator<DeptLevelDTO>() {
            @Override
            public int compare(DeptLevelDTO o1, DeptLevelDTO o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });

        // 递归生成树
        transformDeptTree(deptLevelList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    private void transformDeptTree(List<DeptLevelDTO> deptLevelList, String level, Multimap<String, DeptLevelDTO> levelDeptMap) {
        for (int i = 0; i < deptLevelList.size(); i++){
            // 遍历该层的每个元素
            DeptLevelDTO deptLevelDTO = deptLevelList.get(i);
            // 处理当前层的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDTO.getId());
            // 处理下一次层
            List<DeptLevelDTO> tempDeptList = (List<DeptLevelDTO>) levelDeptMap.get(nextLevel);
            if(!CollectionUtils.isEmpty(tempDeptList)){
                // 排序
                tempDeptList.sort(new Comparator<DeptLevelDTO>() {
                    @Override
                    public int compare(DeptLevelDTO o1, DeptLevelDTO o2) {
                        return o1.getSeq() - o2.getSeq();
                    }
                });
                // 设置下一个部门
                deptLevelDTO.setDeptList(tempDeptList);
                // 进入到下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }

    }
}
