package org.piotrek.spaceinvaders;

import dagger.Component;
import org.piotrek.spaceinvaders.controller.ControllerModule;
import org.piotrek.spaceinvaders.model.ModelModule;
import org.piotrek.spaceinvaders.view.ViewModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ModelModule.class, ViewModule.class, ControllerModule.class})
public interface EngineComponent {

	Engine createEngine();

}
