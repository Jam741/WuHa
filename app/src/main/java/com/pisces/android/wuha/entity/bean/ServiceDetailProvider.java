package com.pisces.android.wuha.entity.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Li on 2017/9/14.
 */

public class ServiceDetailProvider {


    /**
     * Id : 5361304320953356438
     * Name : 成都杜甫草堂博物馆
     * ServiceProviderAddress : {"Id":"4941700888876585476","Latitude":104.034848,"Longitude":30.666397,"MainAddressLine":"青华路37号","Name":"主要地址"}
     * ServiceProviderContact : {"Id":"5743370408263164600","Name":"主要联系方式","Phone":"083387654321","MobilePhone":"98764567102","OrtherContact":"OtherContact"}
     * ServiceProviderCertificationInfo : {"Id":"4981901421985701438","BusinessLicenseImagePath":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504340601&di=0c4be8cf6a460c51b9ab0d222eb48dbe&imgtype=jpg&er=1&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201406%2F27%2F20140627085544_cHFnx.jpeg","LegalPersonInfoImagePath":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503745927460&di=737d4db400e280d58eb23ac26c9eebea&imgtype=0&src=http%3A%2F%2Fpic20.nipic.com%2F20120403%2F7885626_091956683185_2.jpg","IsCertificationInfo":0}
     * ServiceProviderIntroduction : {"Id":"5605410605027501871","Introduction":"这里是成都杜甫草堂博物馆的简单介绍","Description":"这里是成都杜甫草堂博物馆的详细描述","ImagePath":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504340745&di=94e8cd756ad398a6f6c4ce132f54253b&imgtype=jpg&er=1&src=http%3A%2F%2Fpic8.nipic.com%2F20100718%2F5401072_123826008131_2.jpg","ServiceProviderBannerPaths":[{"Id":33,"Path":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503746006389&di=0db690e39e4e0570d259395a0d3ed52e&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F18%2F09%2F79%2F55d1ed2c1098a_1024.jpg"},{"Id":34,"Path":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503746006378&di=9b3160bc22fb15d3939c593e198079cf&imgtype=0&src=http%3A%2F%2Fimg155.poco.cn%2Fmypoco%2Fmyphoto%2F20090611%2F14%2F12279062200906111412311756765842618_009.jpg"}]}
     * ServiceProviderServiceCategories : [{"Id":"4612561924395694951","Name":"宠物手术","SubCategories":[{"Id":"5156293244959719619","Name":"中型手术","Price":9800},{"Id":"5182525672232723732","Name":"大型手术","Price":15800},{"Id":"5544385201366288029","Name":"小型手术","Price":6800}]},{"Id":"4794391862103782756","Name":"清创","SubCategories":[{"Id":"4837539746106540850","Name":"小面积","Price":3200},{"Id":"4997100854385977888","Name":"大面积","Price":8800}]},{"Id":"4946134811791848949","Name":"诊断服务","SubCategories":[{"Id":"4750213658882502981","Name":"VIP服务","Price":620},{"Id":"5233071810722233772","Name":"初级诊断","Price":80},{"Id":"5638434957439061686","Name":"急诊","Price":120}]}]
     * StartingPrice : 80
     * ViewingCount : 3
     * CreateTime : 2017-08-26T00:00:00
     * ServiceProviderType : 1
     * Distance : 134411.8749150157
     */

    private String Id;
    private String Name;
    private ServiceProviderAddressBean ServiceProviderAddress;
    private ServiceProviderContactBean ServiceProviderContact;
    private ServiceProviderCertificationInfoBean ServiceProviderCertificationInfo;
    private ServiceProviderIntroductionBean ServiceProviderIntroduction;
    private int StartingPrice;
    private int ViewingCount;
    private String CreateTime;
    private int ServiceProviderType;
    private double Distance;
    private ArrayList<ServiceProviderServiceCategoriesBean> ServiceProviderServiceCategories;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public ServiceProviderAddressBean getServiceProviderAddress() {
        return ServiceProviderAddress;
    }

    public void setServiceProviderAddress(ServiceProviderAddressBean ServiceProviderAddress) {
        this.ServiceProviderAddress = ServiceProviderAddress;
    }

    public ServiceProviderContactBean getServiceProviderContact() {
        return ServiceProviderContact;
    }

    public void setServiceProviderContact(ServiceProviderContactBean ServiceProviderContact) {
        this.ServiceProviderContact = ServiceProviderContact;
    }

    public ServiceProviderCertificationInfoBean getServiceProviderCertificationInfo() {
        return ServiceProviderCertificationInfo;
    }

    public void setServiceProviderCertificationInfo(ServiceProviderCertificationInfoBean ServiceProviderCertificationInfo) {
        this.ServiceProviderCertificationInfo = ServiceProviderCertificationInfo;
    }

    public ServiceProviderIntroductionBean getServiceProviderIntroduction() {
        return ServiceProviderIntroduction;
    }

    public void setServiceProviderIntroduction(ServiceProviderIntroductionBean ServiceProviderIntroduction) {
        this.ServiceProviderIntroduction = ServiceProviderIntroduction;
    }

    public int getStartingPrice() {
        return StartingPrice;
    }

    public void setStartingPrice(int StartingPrice) {
        this.StartingPrice = StartingPrice;
    }

    public int getViewingCount() {
        return ViewingCount;
    }

    public void setViewingCount(int ViewingCount) {
        this.ViewingCount = ViewingCount;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public int getServiceProviderType() {
        return ServiceProviderType;
    }

    public void setServiceProviderType(int ServiceProviderType) {
        this.ServiceProviderType = ServiceProviderType;
    }

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double Distance) {
        this.Distance = Distance;
    }

    public ArrayList<ServiceProviderServiceCategoriesBean> getServiceProviderServiceCategories() {
        return ServiceProviderServiceCategories;
    }

    public void setServiceProviderServiceCategories(ArrayList<ServiceProviderServiceCategoriesBean> ServiceProviderServiceCategories) {
        this.ServiceProviderServiceCategories = ServiceProviderServiceCategories;
    }

    public static class ServiceProviderAddressBean {
        /**
         * Id : 4941700888876585476
         * Latitude : 104.034848
         * Longitude : 30.666397
         * MainAddressLine : 青华路37号
         * Name : 主要地址
         */

        private String Id;
        private double Latitude;
        private double Longitude;
        private String MainAddressLine;
        private String Name;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public double getLatitude() {
            return Latitude;
        }

        public void setLatitude(double Latitude) {
            this.Latitude = Latitude;
        }

        public double getLongitude() {
            return Longitude;
        }

        public void setLongitude(double Longitude) {
            this.Longitude = Longitude;
        }

        public String getMainAddressLine() {
            return MainAddressLine;
        }

        public void setMainAddressLine(String MainAddressLine) {
            this.MainAddressLine = MainAddressLine;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }

    public static class ServiceProviderContactBean {
        /**
         * Id : 5743370408263164600
         * Name : 主要联系方式
         * Phone : 083387654321
         * MobilePhone : 98764567102
         * OrtherContact : OtherContact
         */

        private String Id;
        private String Name;
        private String Phone;
        private String MobilePhone;
        private String OrtherContact;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getMobilePhone() {
            return MobilePhone;
        }

        public void setMobilePhone(String MobilePhone) {
            this.MobilePhone = MobilePhone;
        }

        public String getOrtherContact() {
            return OrtherContact;
        }

        public void setOrtherContact(String OrtherContact) {
            this.OrtherContact = OrtherContact;
        }
    }

    public static class ServiceProviderCertificationInfoBean {
        /**
         * Id : 4981901421985701438
         * BusinessLicenseImagePath : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504340601&di=0c4be8cf6a460c51b9ab0d222eb48dbe&imgtype=jpg&er=1&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201406%2F27%2F20140627085544_cHFnx.jpeg
         * LegalPersonInfoImagePath : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503745927460&di=737d4db400e280d58eb23ac26c9eebea&imgtype=0&src=http%3A%2F%2Fpic20.nipic.com%2F20120403%2F7885626_091956683185_2.jpg
         * IsCertificationInfo : 0
         */

        private String Id;
        private String BusinessLicenseImagePath;
        private String LegalPersonInfoImagePath;
        private int IsCertificationInfo;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getBusinessLicenseImagePath() {
            return BusinessLicenseImagePath;
        }

        public void setBusinessLicenseImagePath(String BusinessLicenseImagePath) {
            this.BusinessLicenseImagePath = BusinessLicenseImagePath;
        }

        public String getLegalPersonInfoImagePath() {
            return LegalPersonInfoImagePath;
        }

        public void setLegalPersonInfoImagePath(String LegalPersonInfoImagePath) {
            this.LegalPersonInfoImagePath = LegalPersonInfoImagePath;
        }

        public int getIsCertificationInfo() {
            return IsCertificationInfo;
        }

        public void setIsCertificationInfo(int IsCertificationInfo) {
            this.IsCertificationInfo = IsCertificationInfo;
        }
    }

    public static class ServiceProviderIntroductionBean {
        /**
         * Id : 5605410605027501871
         * Introduction : 这里是成都杜甫草堂博物馆的简单介绍
         * Description : 这里是成都杜甫草堂博物馆的详细描述
         * ImagePath : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504340745&di=94e8cd756ad398a6f6c4ce132f54253b&imgtype=jpg&er=1&src=http%3A%2F%2Fpic8.nipic.com%2F20100718%2F5401072_123826008131_2.jpg
         * ServiceProviderBannerPaths : [{"Id":33,"Path":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503746006389&di=0db690e39e4e0570d259395a0d3ed52e&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F18%2F09%2F79%2F55d1ed2c1098a_1024.jpg"},{"Id":34,"Path":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503746006378&di=9b3160bc22fb15d3939c593e198079cf&imgtype=0&src=http%3A%2F%2Fimg155.poco.cn%2Fmypoco%2Fmyphoto%2F20090611%2F14%2F12279062200906111412311756765842618_009.jpg"}]
         */

        private String Id;
        private String Introduction;
        private String Description;
        private String ImagePath;
        private List<ServiceProviderBannerPathsBean> ServiceProviderBannerPaths;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getIntroduction() {
            return Introduction;
        }

        public void setIntroduction(String Introduction) {
            this.Introduction = Introduction;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String ImagePath) {
            this.ImagePath = ImagePath;
        }

        public List<ServiceProviderBannerPathsBean> getServiceProviderBannerPaths() {
            return ServiceProviderBannerPaths;
        }

        public void setServiceProviderBannerPaths(List<ServiceProviderBannerPathsBean> ServiceProviderBannerPaths) {
            this.ServiceProviderBannerPaths = ServiceProviderBannerPaths;
        }

        public static class ServiceProviderBannerPathsBean {
            /**
             * Id : 33
             * Path : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503746006389&di=0db690e39e4e0570d259395a0d3ed52e&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F18%2F09%2F79%2F55d1ed2c1098a_1024.jpg
             */

            private int Id;
            private String Path;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getPath() {
                return Path;
            }

            public void setPath(String Path) {
                this.Path = Path;
            }
        }
    }

    public static class ServiceProviderServiceCategoriesBean {
        /**
         * Id : 4612561924395694951
         * Name : 宠物手术
         * SubCategories : [{"Id":"5156293244959719619","Name":"中型手术","Price":9800},{"Id":"5182525672232723732","Name":"大型手术","Price":15800},{"Id":"5544385201366288029","Name":"小型手术","Price":6800}]
         */

        private String Id;
        private String Name;
        private ArrayList<SubCategoriesBean> SubCategories;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public ArrayList<SubCategoriesBean> getSubCategories() {
            return SubCategories;
        }

        public void setSubCategories(ArrayList<SubCategoriesBean> SubCategories) {
            this.SubCategories = SubCategories;
        }

        public static class SubCategoriesBean {
            /**
             * Id : 5156293244959719619
             * Name : 中型手术
             * Price : 9800
             */

            private String Id;
            private String Name;
            private int Price;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getPrice() {
                return Price;
            }

            public void setPrice(int Price) {
                this.Price = Price;
            }
        }
    }

}
