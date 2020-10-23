/*
 * Copyright 2001-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.ureportdemo.mapper;

import com.example.ureportdemo.entity.UreportFileEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/10/23 16:11
 */
@Mapper
public interface ReportMapper {
    /**
     *  根据报表名称检查报表是否存在
     * @param name 报表名称
     * @return
     */
    int checkExistByName(String name);

    /**
     *  根据报表名称查询报表
     * @param name 报表名称
     * @return
     */
    UreportFileEntity queryUreportFileEntityByName(String name);

    /**
     * 查询全部报表
     * @return
     */
    List<UreportFileEntity> queryReportFileList();

    /**
     * 根据报表名称删除报表
     * @param name
     * @return
     */
    int deleteReportFileByName(String name);


    /**
     *  保存报表
     */
    int insertReportFile(UreportFileEntity entity);

    /**
     *  更新报表
     * @param entity
     * @return
     */
    int updateReportFile(UreportFileEntity entity);
}
