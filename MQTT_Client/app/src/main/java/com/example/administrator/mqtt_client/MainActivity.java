package com.example.administrator.mqtt_client;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MainActivity extends AppCompatActivity {

    private Button btnMQTT_Connect;
    private Button btnMQTT_Publish;
    private Button btnMQTT_Publish2;
    private CheckBox chbLEDSW;

    boolean ignoreChange = false;

    private String host = "tcp://nodemcu_3037.mqtt.iot.gz.baidubce.com:1883";
    private String userName = "nodemcu_3037/esp8266";
    private String passWord = "v9dPu0sRx+FteA0+NC7IVrfCQENuB1Dnx9VhN62eM4A=";
    private String messageBuf = "1";
    private String ON_LED = "LED:on";
    private String OFF_LED = "LED:off";
    private String STATE_LED = "LED:state";
    private int qos = 0;

    private MqttClient client;
    private MqttConnectOptions options;


    private String myTopic = "LED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMQTT_Connect = (Button) findViewById(R.id.btnMQTT_Connect);
        btnMQTT_Publish = (Button) findViewById(R.id.btnMQTT_Publish);
        btnMQTT_Publish2 = (Button) findViewById(R.id.btnMQTT_Publish2);
        chbLEDSW = (CheckBox) findViewById(R.id.chbLEDSW);

        MQTT_Init();
        Connect();
        try {//订阅LED
            client.subscribe(myTopic,qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        //  发送查询LED状态消息
        MqttMessage msg = new MqttMessage(STATE_LED.getBytes());
        msg.setQos(qos);
        try {//发布消息
            client.publish(myTopic,msg);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        btnMQTT_Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MQTT_Init();
                Connect();
            }
        });
        chbLEDSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!ignoreChange){
                    if(b){
                        MqttMessage msg = new MqttMessage(ON_LED.getBytes());
                        msg.setQos(qos);
                        try {//发布消息
                            client.publish(myTopic,msg);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(MainActivity.this,"on",Toast.LENGTH_SHORT).show();
                    }else {
                        MqttMessage msg = new MqttMessage(OFF_LED.getBytes());
                        msg.setQos(qos);
                        try {//发布消息
                            client.publish(myTopic,msg);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(MainActivity.this,"off",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String string = msg.obj.toString();
            switch (msg.what){
                case 0:
                    //  判断LED查询状态
                    if(string.equals("LED_state:on")||string.equals("LED:on")){
                        ignoreChange = true;
                        chbLEDSW.setChecked(true);
                        ignoreChange = false;
                    }else if (string.equals("LED_state:off")||string.equals("LED:off")){
                        ignoreChange = true;
                        chbLEDSW.setChecked(false);
                        ignoreChange = false;
                    }
                    break;
                case 1:
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        }
    };
    public void MQTT_Init(){
        try {
            //host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(host,"222",new MemoryPersistence());
            //MQTT连接设置
            options = new MqttConnectOptions();
            //设置回调
            client.setCallback(mqttCallback);
            //设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            //设置连接的用户名
            options.setUserName(userName);
            //设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void Connect(){
        try {
            //连接MQTT服务器
            client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public MqttCallback mqttCallback = new MqttCallback() {
        @Override
        public void connectionLost(Throwable cause) {

        }
        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            //收到订阅消息
            String string = new String(message.getPayload());
            Message msg = new Message();
            msg.obj = string;
            msg.what = 0;
            handler.sendMessage(msg);
        }
        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {

        }
    };
}