package com.solvo.hoam.presentation.mvp.presenter;

import com.solvo.hoam.di.ApplicationComponent;
import com.solvo.hoam.domain.interactor.SplashInteractor;
import com.solvo.hoam.presentation.mvp.view.SplashView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {

    private static final String TAG = SplashPresenter.class.getSimpleName();
    private CompositeDisposable compositeDisposable;

    @Inject
    SplashInteractor interactor;

    public SplashPresenter(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);

        compositeDisposable = new CompositeDisposable();
    }

    public void fetchData() {
        compositeDisposable.add(interactor.fetchData().subscribe(() -> handleSuccess(), throwable -> handleError()));
    }

    private void handleError() {
        getViewState().showError(true);
    }

    private void handleSuccess() {
        getViewState().navigateToMainScreen();
    }

    public void onTryAgainClicked() {
        getViewState().showError(false);
        fetchData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
