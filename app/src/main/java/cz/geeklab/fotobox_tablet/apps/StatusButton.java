package cz.geeklab.fotobox_tablet.apps;

/**
 * Created by Jaroslav on 20. 6. 2015.
 */
public class StatusButton {

    private TouchButtonState State = TouchButtonState.BUTTON_UP;


    public TouchButtonState getState() {
        return State;
    }

    public void setState(TouchButtonState state) {
        State = state;
    }

    public enum TouchButtonState {
        BUTTON_DOWN ("ZAPNUTO"), BUTTON_UP ("VYPNUTO");


        private final String name;

        private TouchButtonState (String s) {
            name = s;
        }

        public boolean equalsName(String otherName){
            return (otherName == null)? false:name.equals(otherName);
        }

        public String toString(){
            return name;
        }
    }



}


