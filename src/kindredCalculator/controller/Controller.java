package kindredCalculator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import kindredCalculator.data.RelationShipData;

import java.util.Stack;

public class Controller {
    private int count = 0;

    @FXML
    private Button daughterButton;

    @FXML
    private Button smallBrotherButton;

    @FXML
    private Button bigBrotherButton;

    @FXML
    private Button fatherButton;

    @FXML
    private Button husbandButton;

    @FXML
    private Button wifeButton;

    @FXML
    private Button bigSisterButton;

    @FXML
    private Button sonButton;

    @FXML
    private Button montherButton;

    @FXML
    private Button smallSisterButton;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
        // 【丈夫】按钮的事件监听器
    void do_husbandButton_event(ActionEvent event) {
        setTextByButton(husbandButton);
    }

    @FXML
        // 【妻子】按钮的事件监听器
    void do_wifeButton_event(ActionEvent event) {
        setTextByButton(wifeButton);
    }

    @FXML
        // 【爸爸】按钮的事件监听器
    void do_fatherButton_event(ActionEvent event) {
        setTextByButton(fatherButton);
    }

    @FXML
        // 【妈妈】按钮的事件监听器
    void do_montherButton_event(ActionEvent event) {
        setTextByButton(montherButton);
    }

    @FXML
        // 【儿子】按钮的事件监听器
    void do_sonButton_event(ActionEvent event) {
        setTextByButton(sonButton);
    }

    @FXML
        // 【女儿】按钮的事件监听器
    void do_daughterButton_event(ActionEvent event) {
        setTextByButton(daughterButton);
    }

    @FXML
        // 【哥哥】按钮的事件监听器
    void do_bigBrotherButton_event(ActionEvent event) {
        setTextByButton(bigBrotherButton);
    }

    @FXML
        // 【弟弟】按钮的事件监听器
    void do_smallBrotherButton_event(ActionEvent event) {
        setTextByButton(smallBrotherButton);
    }

    @FXML
        // 【姐姐】按钮的事件监听器
    void do_bigSisterButton_event(ActionEvent event) {
        setTextByButton(bigSisterButton);
    }

    @FXML
        // 【妹妹】按钮的事件监听器
    void do_smallSisterButton_event(ActionEvent event) {
        setTextByButton(smallSisterButton);
    }

    @FXML
        // 【回退】按钮的事件监听器
    void do_rebackButton_event(ActionEvent event) {
        String[] array = inputTextArea.getText().split("的");
        Stack<String> stack = new Stack<>();
        for (String str : array) {
            stack.push(str);
        }
        stack.pop();
        String string = "";
        for (String str : stack) {
            string += str + "的";
        }
        int index = string.length();
        if (index == 0) {
            inputTextArea.setText("我");
        } else {
            string = string.substring(0, index - 1);
            inputTextArea.setText(string);
        }
    }

    @FXML
        // 【清空】按钮的事件监听器
    void do_clearButton_event(ActionEvent event) {
        inputTextArea.setText("我");
        outputTextArea.setText("");
    }

    @FXML
        // 【计算】按钮的事件监听器
    void do_countButton_event(ActionEvent event) {
        String[][] relationshipData = new RelationShipData().getRelationShipData();
        String[] array = inputTextArea.getText().split("的");
        int column = 0, row = 0;
        String resultValue = array[0];
        for (int i = 1; i < array.length; i++) {
            for (int m = 0; m < relationshipData.length; ++m) {
                if (relationshipData[m][0].equals(resultValue)) {
                    row = m;
                }
            }
            for (int n = 0; n < relationshipData[0].length; n++) {
                if (relationshipData[0][n].equals(array[i])) {
                    column = n;
                }
            }
            resultValue = relationshipData[row][column];
            if (!isExist(resultValue, relationshipData)) {
                resultValue = "未知亲戚";
                break;
            }
        }
        outputTextArea.setText(resultValue);
    }

    /**
     * 操作结果：根据按钮设置文本
     *
     * @param button 按钮
     */
    public void setTextByButton(Button button) {
        // 将累计的按钮点击次数清零，重新计数
        if (inputTextArea.getText().equals(null) || inputTextArea.getText().equals("")) {
            count = 0;
        }
        String name = "";
        if (count == 0) {
            name = button.getText();
        } else {
            name = "的" + button.getText();
        }
        count++;
        inputTextArea.appendText(name);
    }

    /**
     * 操作结果：判断某个值在二维数组中的行首值中是否存在
     *
     * @param value 某个值
     * @param array 二维数组
     * @return 如果存在返回true，否则返回false
     */
    public boolean isExist(String value, String[][] array) {
        for (int i = 0; i < array.length; i++) {
            if (value.equals(array[i][0])) {
                return true;
            }
        }
        return false;
    }
}
