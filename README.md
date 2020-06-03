#  submodule사용하기

간단하게 submodule동작을 확인하는 프로젝트입니다.

## 1. common 모듈 만들기

공통 컴포넌트를 뽑아내 모듈화하여 공유하고
앞으로 진행할 프로젝트에서 공통모듈을 사용하여 개별 구현하는 시간을 줄여보려고 합니다.  

테스트로 간단한게 자주 사용되는 Lottie기능을 :common으로 모듈화해서,  
testProject에서 가져다가 써볼려고 합니다.  

이렇게 하게되면 모든 프로젝트에서 공용으로 사용되는 부분은 :common모듈로 구현되고,  
불필요하게 각 프로젝트마다 공용으로 사용되는 기능을 각각 구현할 필요가 없어집니다.(네트워크모듈, 공통UI컴포넌트, 팝업 등...)

### 1) 공통 모듈 추가(:common)

새로운 프로젝트를 하나 생성하고, 공통으로 사용될 모듈 :common 모듈을 추가합니다.

> File > New > New Module > Android Library > Finish


### 2) 공통으로 사용할 dependency 추가
:common모듈 수준의 build.gradle을 열어서 dependencies에 Lottie라이브러리를 추가합니다.

```gradle
dependencies {
    api 'com.airbnb.android:lottie:3.4.0'
}
```
주목할 점은 implementation이 아니라 api라는 것입니다.  
api로 Lottie를 가져와야 :common모듈을 사용하는 곳에서도 share dependency로 가져다 쓸수 있습니다.  
[api와 implementation의 차이](https://docs.gradle.org/current/userguide/java_library_plugin.html#sec:java_library_separation)


### 3) 공통으로 사용할 부분 구현

공용으로 사용될 부분을 구현합니다. LottieAnimationView의 확장함수를 하나 만들었습니다.  

### 4) AndroidManifest 수정

:common모듈의 AndroidManifest의 intent-filte를 모두 제거 합니다.

```xml
//인텐트 필터를 모두 제거 합니다.
           <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
```

### 5) RemoteRepository Push
현재까지 작업한 내용을 모두 commit하고 https://github.ncsoft.com/mobiledev/submoduleCommon.git 에 push합니다.
```git
git commit -a
git remote add origin https://github.ncsoft.com/mobiledev/submoduleCommon.git
git remote push --set-upstream origin master
```
이제 :common모듈이 완성되었습니다. 아래의 사용하는 프로젝트에서 라이브러리화된 모듈을 사용하는 법을 설명합니다.  
이 공통 모듈:common을 사용하는 프로젝트는 :common만 submodule화 하면 LottieAnimationView.loadDefaultLottie()함수를 사용할 수 있습니다.

## 2. 사용하는 프로젝트에서

현재 프로젝트에서 Lottie를 사용하되, 추가된 submodule의 dependency를 이용해 사용할 것입니다.
(현재 프로젝트의 build.gradle에는 Lottie depdency 추가하지 않음)


### 1) git 서브모듈 주소를 추가
```git
git submodule add https://github.ncsoft.com/mobiledev/submoduleCommon.git
```

그럼 이런 결과가 나옵니다.
```
kimdongikuiiMac:testProject kimdongsik$ git submodule add https://github.ncsoft.com/mobiledev/submoduleCommon.git
Cloning into '/Users/kimdongsik/testProject/submoduleCommon'...
remote: Enumerating objects: 172, done.
remote: Counting objects: 100% (172/172), done.
remote: Compressing objects: 100% (77/77), done.
remote: Total 172 (delta 32), reused 172 (delta 32), pack-reused 0
Receiving objects: 100% (172/172), 95.08 KiB | 19.02 MiB/s, done.
Resolving deltas: 100% (32/32), done.
kimdongikuiiMac:testProject kimdongsik$ 

```
이제 common을 내 프로젝트 밑으로 가져왔습니다.  
하지만 아직 common과 app모듈을 연결하지 않아 common모듈에 안에 있는 클래스를 사용하진 못합니다.  
### 2) 내 프로젝트에 module로 Import
> Android Studio menu의 File > New > Import Module > Finder로 내 프로젝트 밑의 common모듈 path 입력 > import 체크 > Finish

이제 common을 내 프로젝트의 하나로 모듈로 추가했습니다.


### 3) 내 프로젝트에 module Dependency로 추가
> Android Studio menu의 file > project Structure > Dependencies > app모듈 선택 > Declared Dependencies탭의 '+'선택 > Module Dependency선택 > common 모듈 선택 > Ok > Apply > Ok

### 4) common의 Lottie 클래스 가져오는것 확인하기
<img src="./submodule.gif" width="70%">
내 프로젝트의 :app모듈에 MainActivity에서 LottieAnim을 치면 자동완성되는것을 확인할 수 있습니다.
내 프로젝트 app Module에는 Lottie Dependency가 없지만, common의 dependepcy가 share되어 접근 가능한것을 볼수 있습니다.

### #추가
submodule(:common)이 업데이트 되었을때는아래의 명령어를 치면 새로운 버전을 땡겨올수 있습니다.
```git
git submodule update --remote
```


