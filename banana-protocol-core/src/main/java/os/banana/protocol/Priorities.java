/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import org.springframework.core.Ordered;

/**
 * 定义了若干个优先级常量，这些常量可以在程序中使用，比如说可以用在{@link Ordered}中，用来帮助排序。
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface Priorities {

	int LEVEL_0 = 0;
	int LEVEL_1 = 1;
	int LEVEL_2 = 2;
	int LEVEL_3 = 3;
	int LEVEL_4 = 4;
	int LEVEL_5 = 5;
	int LEVEL_6 = 6;
	int LEVEL_7 = 7;
	int LEVEL_8 = 8;
	int LEVEL_9 = 9;
	int LEVEL_10 = 10;
	int LEVEL_MAX = Integer.MAX_VALUE;
}
