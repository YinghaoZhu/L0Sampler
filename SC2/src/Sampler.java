/** 
* @author 887402 Yinghao Zhu E-mail: zhu156323225@icloud.com
* @version time：10Oct.,2018 1:42:06 pm 
* 
*/

public interface Sampler<T> {

    public void add(T item, int value);
    public T output();
}
