package com.csdn.jinxu.weka.cluster.em;

import weka.clusterers.SimpleKMeans;
import weka.core.EuclideanDistance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.io.File;

/**
 * 实现描述：K-Means聚类
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-9-4 下午1:32
 */
public class WekaEMCluster {

    public static SimpleKMeans train(Instances instances, String[] options) throws Exception {
        SimpleKMeans cluster = new SimpleKMeans();
        if (null != options) {
            cluster.setOptions(options);
        }
        cluster.setNumClusters(2);
        cluster.setDistanceFunction(new EuclideanDistance());
        cluster.buildClusterer(instances);

        return cluster;
    }

    public static Instances generate(String arffFileName, String[] options) throws Exception {
        ArffLoader atf = new ArffLoader();
        File inputFile = new File(arffFileName);
        atf.setFile(inputFile);
        /**格式化的训练数据*/
        Instances instancesTrain = atf.getDataSet();

        if (null != options) {
            Remove remove = new Remove();
            remove.setOptions(options);
            remove.setInputFormat(instancesTrain);
            instancesTrain = Filter.useFilter(instancesTrain, remove);
        }
        return instancesTrain;
    }

}
