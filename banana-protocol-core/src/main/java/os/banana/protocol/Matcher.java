/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

/**
 * 这个接口定义了一个match方法，可以用在需要进行适配的情况下，比如说可以用在CommandController中，
 * 可以拓展CommandController原本的根据注解进行适配的情况，让CommandController可以通过实现该接口的方式来完成更加灵活的控制。
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface Matcher<T> {

	boolean match(T target);
}
