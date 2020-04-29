# ZL-NetworkLiveData

#### 使用方法

```
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NetworkLiveData.getInstance().observe(this, Observer {
            when (it) {
                NetworkState.CONNECT -> {
                    text.text = "网络已连接"
                }

                NetworkState.NONE -> {
                    text.text = "网络中断"
                }

                NetworkState.CELLULAR -> {
                    text.text = "移动网络已连接"
                }

                NetworkState.WIFI -> {
                    text.text = "WIFI已连接"
                }
            }
        })
    }
}
```