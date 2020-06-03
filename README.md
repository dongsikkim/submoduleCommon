# 1. 사용하는 프로젝트에서

### 1) 서브모듈 주소를 추가
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


<img src="./submodule.gif" width="70%">
내 프로젝트의 :app모듈에 MainActivity에서 LottieAnim을 치면 자동완성되는것을 확인할 수 있습니다.
내 프로젝트 app Module에는 Lottie Dependency가 없지만, common의 dependepcy가 share되어 접근 가능한것을 볼수 있습니다.

### #추가
submodule(:common)이 업데이트 되었을때는아래의 명령어를 치면 새로운 버전을 땡겨올수 있습니다.
```git
git submodule update --remote
```


