package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.model.project.Project;

import java.util.List;

/**
 * Created by andrew on 07.06.2019.
 */

public interface ProjectsView extends BaseView {
    void showProjects(List<Project> projects);
    void openProfileFragment(String username);

}
