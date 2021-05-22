<template>
  <div>

    <!-- 阿里云视频播放器样式 -->
    <link rel="stylesheet" href="https://g.alicdn.com/de/prismplayer/2.8.2/skins/default/aliplayer-min.css" >
    <!-- 启用私有加密的防调式 -->
    <script src="https://g.alicdn.com/de/prismplayer/2.8.0/hls/aliplayer-vod-anti-min.js"/>
    <!-- 阿里云视频播放器脚本 -->
    <script charset="utf-8" type="text/javascript" src="https://g.alicdn.com/de/prismplayer/2.8.2/aliplayer-min.js"/>
<!--    <script charset="utf-8" type="text/javascript" src="edu-online-project/assets/js/aliplayer-min.js"/>-->
<!--    <script src="https://g.alicdn.com/de/prismplayer/2.7.2/aliplayer-min.js"></script>-->
<!--    <script src="https://g.alicdn.com/de/prismplayer/2.7.2/aliplayer-h5-min.js"></script>-->

    <!--组件-->
    <script type="text/javascript" charset="utf-8" src="https://player.alicdn.com/aliplayer/presentation/js/aliplayercomponents.min.js"/>

    <!-- 定义播放器dom -->
    <div id="J_prismPlayer" class="prism-player"/>
  </div>
</template>

<script>
import vod from '@/api/vod'
export default {

  layout: 'video',
  asyncData({ params, error }) {
    return vod.getPlayVideoAuth(params.vid).then(response => {
      return {
        vid: params.vid,
        playAuth: response.data.data.playAuth
      }
    })
  },

  /**
 * 页面渲染完成时：此时js脚本已加载，Aliplayer已定义，可以使用
 * 如果在created生命周期函数中使用，Aliplayer is not defined错误
 */
  mounted() {
    /* 关闭广告的自定义事件, 可自行修改代码从而满足不同的功能, 参数为视频广告组件本身 */
    const videoAdClose = function(videoAd) {
    /* 调用视频广告组件的暂停事件来暂停广告 */
      videoAd.pauseVideoAd()
      var result = confirm('确定开通会员关闭广告吗？')
      if (result) {
      /* 调用视频广告组件关闭事件来关闭广告 */
        // 跳转到开通会员页面
        videoAd.closeVideoAd()
      } else {
      /* 调用视频广告组件的播放事件来播放广告 */
        videoAd.playVideoAd()
      }
    }

    /* 弹幕组件集成了 CommentCoreLibrary 框架, 更多 Api 请查看文档 https://github.com/jabbany/CommentCoreLibrary/ */
    var danmukuList = [{
      'mode': 1,
      'text': '哈哈',
      'stime': 1000,
      'size': 25,
      'color': 0xffff00
    }, {
      'mode': 1,
      'text': '前方高能',
      'stime': 2000,
      'size': 25,
      'color': 0xff00ff
    }, {
      'mode': 1,
      'text': '灵魂歌手',
      'stime': 30000,
      'size': 25,
      'color': 0x00ff00
    }, {
      'mode': 1,
      'text': '这是弹幕2',
      'stime': 4000,
      'size': 25,
      'color': 0x00c1de
    }, {
      'mode': 1,
      'text': '神测试',
      'stime': 5000,
      'size': 25,
      'color': 0x0000ff
    }, {
      'mode': 1,
      'text': '顺手一划',
      'stime': 10000,
      'size': 25,
      'color': 0x00c1de
    }, {
      'mode': 1,
      'text': '哈哈',
      'stime': 1000,
      'size': 25,
      'color': 0xffffff
    }, {
      'mode': 1,
      'text': '哈哈',
      'stime': 1000,
      'size': 25,
      'color': 0xffffff
    }]

    /* eslint-disable no-undef */
    const player = new Aliplayer({
      id: 'J_prismPlayer',
      vid: this.vid, // 视频id
      playauth: this.playAuth, // 播放凭证
      encryptType: '1', // 如果播放加密视频，则需设置encryptType=1，非加密视频无需设置此项
      width: '100%',
      height: '500px',

      // 以下可选设置
      // cover: 'http://video.yaohuan.work/image/default/DF3E77AA7D894DA5975068C5F5201D63-6-2.jpg', // 封面
      qualitySort: 'asc', // 清晰度排序

      // format: 'm3u8', // 播放格式
      mediaType: 'video', // 返回音频还是视频
      autoplay: false, // 自动播放
      isLive: false, // 直播
      rePlay: false, // 循环播放
      preload: true,
      controlBarVisibility: 'hover', // 控制条的显示方式：鼠标悬停
      useH5Prism: true, // 播放器类型：html5

      components: [
      // 跑马灯组件
        {
          name: 'BulletScreenComponent',
          type: AliPlayerComponent.BulletScreenComponent,
          /** 跑马灯组件三个参数 text, style, bulletPosition
       * text: 跑马灯文字内容
       * style: 跑马灯样式
       * bulletPosition: 跑马灯位置, 可选的值为 'top' (顶部), 'bottom' (底部), 'random' (随机), 不传值默认为 'random'
       */
          args: ['Helen，欢迎使用阿里播放器', { fontSize: '16px', color: '#00c1de' }, 'random']
        },
        // 视频广告组件
        // {
        //   name: 'VideoADComponent',
        //   type: AliPlayerComponent.VideoADComponent,
        //   /* 视频广告四个参数 adVideoSource, adLink, adCloseFunction, closeText
        //   * adVideoSource {@String 广告视频的视频地址 必须参数}
        //   * adLink {@String 广告视频的链接地址 必须参数}
        //   * adCloseFunction {@Function 关闭广告的点击事件处理函数, 可选参数, 不传则默认关闭广告视频}
        //   * closeText {@String 关闭广告的文字内容, 可选参数, 不传则默认为 '关闭广告'}
        //   */
        //   args: ['http://player.alicdn.com/ad/citybrain.mp4', 'https://et.aliyun.com/brain/city', videoAdClose, 'VIP关闭广告']
        // },
        // 弹幕组件
        {
          name: 'AliplayerDanmuComponent',
          type: AliPlayerComponent.AliplayerDanmuComponent,
          args: [danmukuList]
        }
      ],

      /* h5截图按钮 */
      'extraInfo': {
        'crossOrigin': 'anonymous'
      },
      'skinLayout': [
        { 'name': 'bigPlayButton', 'align': 'blabs', 'x': 30, 'y': 80 },
        { 'name': 'H5Loading', 'align': 'cc' },
        { 'name': 'errorDisplay', 'align': 'tlabs', 'x': 0, 'y': 0 },
        { 'name': 'infoDisplay' },
        { 'name': 'tooltip', 'align': 'blabs', 'x': 0, 'y': 56 },
        { 'name': 'thumbnail' },
        {
          'name': 'controlBar', 'align': 'blabs', 'x': 0, 'y': 0,
          'children': [
            { 'name': 'progress', 'align': 'blabs', 'x': 0, 'y': 44 },
            { 'name': 'playButton', 'align': 'tl', 'x': 15, 'y': 12 },
            { 'name': 'timeDisplay', 'align': 'tl', 'x': 10, 'y': 7 },
            { 'name': 'fullScreenButton', 'align': 'tr', 'x': 10, 'y': 12 },
            { 'name': 'subtitle', 'align': 'tr', 'x': 15, 'y': 12 },
            { 'name': 'setting', 'align': 'tr', 'x': 15, 'y': 12 },
            { 'name': 'volume', 'align': 'tr', 'x': 5, 'y': 10 },
            { 'name': 'snapshot', 'align': 'tr', 'x': 10, 'y': 12 }
          ]
        }
      ]
    }, function(player) {
      console.log('播放器创建成功')
    })

    /* h5截图按钮, 截图成功回调 */
    player.on('snapshoted', function(data) {
      var pictureData = data.paramData.base64
      var downloadElement = document.createElement('a')
      downloadElement.setAttribute('href', pictureData)
      var fileName = 'Aliplayer' + Date.now() + '.png'
      downloadElement.setAttribute('download', fileName)
      downloadElement.click()
      pictureData = null
    })
  }
}
</script>


<!--<style lang="postcss" scoped>-->
<!--  @import 'https://g.alicdn.com/de/prismplayer/2.7.2/skins/default/aliplayer-min.css';-->
<!--</style>-->
