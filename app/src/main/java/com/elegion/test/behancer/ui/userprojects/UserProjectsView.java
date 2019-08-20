package com.elegion.test.behancer.ui.userprojects;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.model.project.Project;

import java.util.List;

/**
 * Created by andrew on 07.07.2019.
 */

public interface UserProjectsView extends BaseView {
        @StateStrategyType(value =  AddToEndStrategy.class)
        void showProjects(@NonNull List<Project> projects);
}
