package com.strong.yujiaapp.receiver;

import android.content.Context;
import android.text.TextUtils;

import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;
import java.util.Map;

public class XiaoMiMessageReceiver extends PushMessageReceiver {
	private String mRegId;
	private long mResultCode = -1;
	private String mReason;
	private String mCommand;
	private String mMessage;
	private String mTopic;
	private String mAlias;
	private String mUserAccount;
	private String mStartTime;
	private String mEndTime;
	private IntentActivity brInteraction;

	@Override
	public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
		mMessage = message.getContent();
		if (!TextUtils.isEmpty(message.getTopic())) {
			mTopic = message.getTopic();
		} else if (!TextUtils.isEmpty(message.getAlias())) {
			mAlias = message.getAlias();
		} else if (!TextUtils.isEmpty(message.getUserAccount())) {
			mUserAccount = message.getUserAccount();
		}
	}

	@Override
	public void onNotificationMessageClicked(Context context, MiPushMessage message) {
		mMessage = message.getContent();
		Map<String, String> extra = message.getExtra();
		String string = extra.get("haiyunkey");
		if (!TextUtils.isEmpty(message.getTopic())) {
			mTopic = message.getTopic();
		} else if (!TextUtils.isEmpty(message.getAlias())) {
			mAlias = message.getAlias();
		} else if (!TextUtils.isEmpty(message.getUserAccount())) {
			mUserAccount = message.getUserAccount();
		}
		/*Intent intent = new Intent(context, GoodsQueryActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Bundle bundle = new Bundle();
		bundle.putString("value", string);
		intent.putExtras(bundle);
		context.startActivity(intent);*/
	}

	@Override
	public void onNotificationMessageArrived(Context context, MiPushMessage message) {
		mMessage = message.getContent();
		if (!TextUtils.isEmpty(message.getTopic())) {
			mTopic = message.getTopic();
		} else if (!TextUtils.isEmpty(message.getAlias())) {
			mAlias = message.getAlias();
		} else if (!TextUtils.isEmpty(message.getUserAccount())) {
			mUserAccount = message.getUserAccount();
		}
	}

	@Override
	public void onCommandResult(Context context, MiPushCommandMessage message) {
		String command = message.getCommand();
		List<String> arguments = message.getCommandArguments();
		String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
		String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
		if (MiPushClient.COMMAND_REGISTER.equals(command)) {
			if (message.getResultCode() == ErrorCode.SUCCESS) {
				mRegId = cmdArg1;
			}
		} else if (MiPushClient.COMMAND_SET_ALIAS.equals(command)) {
			if (message.getResultCode() == ErrorCode.SUCCESS) {
				mAlias = cmdArg1;
			}
		} else if (MiPushClient.COMMAND_UNSET_ALIAS.equals(command)) {
			if (message.getResultCode() == ErrorCode.SUCCESS) {
				mAlias = cmdArg1;
			}
		} else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC.equals(command)) {
			if (message.getResultCode() == ErrorCode.SUCCESS) {
				mTopic = cmdArg1;
			}
		} else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC.equals(command)) {
			if (message.getResultCode() == ErrorCode.SUCCESS) {
				mTopic = cmdArg1;
			}
		} else if (MiPushClient.COMMAND_SET_ACCEPT_TIME.equals(command)) {
			if (message.getResultCode() == ErrorCode.SUCCESS) {
				mStartTime = cmdArg1;
				mEndTime = cmdArg2;
			}
		}
	}

	@Override
	public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
		String command = message.getCommand();
		List<String> arguments = message.getCommandArguments();
		String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
		String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
		if (MiPushClient.COMMAND_REGISTER.equals(command)) {
			if (message.getResultCode() == ErrorCode.SUCCESS) {
				mRegId = cmdArg1;
			}
		}
	}

	public interface IntentActivity {
		public void setText(String content);
	}

	public void setBRInteractionListener(IntentActivity brInteraction) {
		this.brInteraction = brInteraction;
	}
}