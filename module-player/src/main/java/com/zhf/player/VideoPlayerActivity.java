package com.zhf.player;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhf.base.activity.MvvmBaseActivity;
import com.zhf.base.livedatabus.LiveDatabus;
import com.zhf.common.adapter.ScreenAutoAdapter;
import com.zhf.common.contract.BaseCustomViewModel;
import com.zhf.common.router.RouterActivityPath;
import com.zhf.player.adapter.ProviderVideoPagerAdapter;
import com.zhf.common.contract.VideoHeaderBean;
import com.zhf.player.databinding.PlayerActivityVideoPlayerBinding;
import com.zhf.player.databinding.PlayerItemVideoHeaderViewBinding;
import com.zhf.video.helper.VideoPlayerHelper;
import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * 应用模块: video
 * <p>
 * 类描述: 视频详情页 & 播放页
 * <p>
 *
 */
@Route(path = RouterActivityPath.Video.PAGER_VIDEO)
public class VideoPlayerActivity extends
    MvvmBaseActivity<PlayerActivityVideoPlayerBinding, VideoPlayerViewModel>
    implements IVideoPlayerView
{
    
    private ProviderVideoPagerAdapter adapter;
    
    @Autowired(name = "videoInfo")
    public VideoHeaderBean headerBean;
    
    private VideoHeaderBean liveHeaderBean;
    
    // 旋转帮助类
    private OrientationUtils orientationUtils;
    
    private boolean isPlay = true;
    
    private boolean isPause;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        ScreenAutoAdapter.match(this, 375.0f);
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        ImmersionBar.with(this).statusBarView(viewDataBinding.topView).init();
        if (headerBean != null)
        {
            initView(headerBean);
        }
        LiveDatabus.getInstance()
            .withSticky("player", VideoHeaderBean.class)
            .observe(this, videoHeaderBean -> {
                liveHeaderBean = videoHeaderBean;
                if (liveHeaderBean != null)
                {
                    initView(liveHeaderBean);
                    Logger.d(liveHeaderBean.toString());
                }
            });
        
    }
    
    private void initView(VideoHeaderBean headerBean)
    {
        viewDataBinding.setBlurred(headerBean.getBlurredUrl());
        viewDataBinding.executePendingBindings();
        initVideoView(headerBean);
        viewDataBinding.rvVideoList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        viewDataBinding.rvVideoList.setLayoutManager(layoutManager);
        adapter = new ProviderVideoPagerAdapter();
        adapter.addHeaderView(getHeaderView(headerBean));
        adapter.addFooterView(getFooterView());
        viewDataBinding.rvVideoList.setAdapter(adapter);
        viewDataBinding.refreshLayout
            .setRefreshHeader(new ClassicsHeader(this));
        viewDataBinding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            // finish()
        });
        setLoadSir(viewDataBinding.refreshLayout);
        showLoading();
        viewModel.initModel();
        viewModel.loadData(headerBean.getVideoId());
    }
    
    private void initVideoView(VideoHeaderBean headerBean)
    {
        
        // 设置返回键
        viewDataBinding.cvVideoView.getBackButton()
            .setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    finish();
                }
            });
        // 全屏辅助
        orientationUtils =
            new OrientationUtils(this, viewDataBinding.cvVideoView);
        // 初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        // 初始化配置
        VideoPlayerHelper.optionPlayer(viewDataBinding.cvVideoView,
            headerBean.getPlayerUrl(),
            true,
            headerBean.getVideoTitle());
        
        // VideoPlayerHelper.clonePlayState(viewDataBinding.cvVideoView);
        
        viewDataBinding.cvVideoView.setIsTouchWiget(true);
        
        viewDataBinding.cvVideoView.setVideoAllCallBack(new GSYSampleCallBack()
        {
            @Override
            public void onPrepared(String url, Object... objects)
            {
                super.onPrepared(url, objects);
                // 开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
            }
            
            @Override
            public void onQuitFullscreen(String url, Object... objects)
            {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null)
                {
                    orientationUtils.backToProtVideo();
                }
            }
        });
        
        viewDataBinding.cvVideoView.startPlayLogic();
    }
    
    private View getFooterView()
    {
        return LayoutInflater.from(this)
            .inflate(R.layout.player_item_footer_white_view,
                viewDataBinding.rvVideoList,
                false);
    }
    
    private View getHeaderView(VideoHeaderBean headerBean)
    {
        if (headerBean != null)
        {
            PlayerItemVideoHeaderViewBinding binding =
                DataBindingUtil.inflate(getLayoutInflater(),
                    R.layout.player_item_video_header_view,
                    viewDataBinding.rvVideoList,
                    false);
            if (binding != null)
            {
                binding.setViewModel(headerBean);
                binding.executePendingBindings();
            }
            return binding.getRoot();
        }
        return null;
    }
    
    @Override
    protected int getLayoutId()
    {
        return R.layout.player_activity_video_player;
    }
    
    @Override
    protected VideoPlayerViewModel getViewModel()
    {
        return ViewModelProviders.of(this).get(VideoPlayerViewModel.class);
    }
    
    @Override
    protected int getBindingVariable()
    {
        return 0;
    }
    
    @Override
    protected void onRetryBtnClick()
    {
        
    }
    
    @Override
    public void onDataLoadFinish(ArrayList<BaseCustomViewModel> viewModels)
    {
        if (viewModels != null)
        {
            adapter.setNewData(viewModels);
            showContent();
        }
    }
    
    @Override
    public void onBackPressed()
    {
        if (orientationUtils != null)
        {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(this))
        {
            return;
        }
        super.onBackPressed();
    }
    
    @Override
    protected void onPause()
    {
        viewDataBinding.cvVideoView.getCurrentPlayer().onVideoPause();
        super.onPause();
        isPause = true;
    }
    
    @Override
    protected void onResume()
    {
        viewDataBinding.cvVideoView.getCurrentPlayer().onVideoResume(false);
        super.onResume();
        isPause = false;
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        viewDataBinding.cvVideoView.getGSYVideoManager()
            .setListener(viewDataBinding.cvVideoView.getGSYVideoManager()
                .lastListener());
        viewDataBinding.cvVideoView.getGSYVideoManager().setLastListener(null);
        viewDataBinding.cvVideoView.cancel();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
        {
            orientationUtils.releaseListener();
            try
            {
                // 第三方库中的内存泄漏,只能利用反射来解决
                Field mOrientationEventListener = OrientationUtils.class
                    .getDeclaredField("mOrientationEventListener");
                Field contextField =
                    OrientationUtils.class.getField("mActivity");
                contextField.setAccessible(true);
                contextField.set(orientationUtils, null);
                mOrientationEventListener.setAccessible(true);
                OrientationEventListener listener =
                    (OrientationEventListener)mOrientationEventListener
                        .get(orientationUtils);
                Field mSensorEventListener = OrientationEventListener.class
                    .getDeclaredField("mSensorEventListener");
                mSensorEventListener.setAccessible(true);
                mSensorEventListener.set(listener, null);
                Field mSensorManager = OrientationEventListener.class
                    .getDeclaredField("mSensorManager");
                mSensorManager.setAccessible(true);
                mSensorManager.set(listener, null);
                
            }
            catch (NoSuchFieldException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            orientationUtils = null;
        }
        VideoPlayerHelper.release();
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        // 如果旋转了就全屏
        if (isPlay && !isPause)
        {
            viewDataBinding.cvVideoView.onConfigurationChanged(this,
                newConfig,
                orientationUtils,
                true,
                true);
        }
    }
    
}
