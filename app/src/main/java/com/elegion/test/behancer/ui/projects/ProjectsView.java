package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.model.project.Project;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by andrew on 07.06.2019.
 */

public interface ProjectsView extends BaseView {
    void showProjects(@NonNull List<Project> projects);
    void openProfileFragment(@NonNull String username);

}
