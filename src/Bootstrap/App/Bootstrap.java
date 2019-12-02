package Bootstrap.App;

import Bootstrap.Parts.AbstractBootstrap;
import Bootstrap.Tools.Alog;

/***
 * @author Aaron Chen
 */

    /*To do list
        1.Initialize Variables
        2.Parse Config File
        3.Parse Java Properties
        4.Parse Environment Variables
        5.Parse Command Line Arguments
        6.Help Determine our Application Config
        7.Start our Application & provide easy access to above
        8.I hadn't put any comment for every method or class、interface etc because Now I just follow Ivan's Google Slides
        After I understand and defined what are they actually doing I will put comment in my idea
        9.Now this project is in JavaDemoInClass repository After finish I will separate to another repository
    * */

public abstract class Bootstrap extends AbstractBootstrap {
    public Bootstrap() {
        super();
    }

    public Bootstrap(String[] optionData) {
        super(optionData);
    }
}
