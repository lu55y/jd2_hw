$ git status
On branch master
Your branch is up to date with 'origin/master'.

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        Task_10-13/
        Task_9/

nothing added to commit but untracked files present (use "git add" to track)

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work (master)
$ git add .
warning: LF will be replaced by CRLF in Task_10-13/testWebApp/pom.xml.
The file will have its original line endings in your working directory
warning: LF will be replaced by CRLF in Task_9/testWebApp/pom.xml.
The file will have its original line endings in your working directory

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work (master)
$ git commit -a -m "taks_9"
[master 359ca9e] taks_9
 5 files changed, 390 insertions(+)
 create mode 100644 Task_10-13/testWebApp/pom.xml
 create mode 100644 Task_10-13/testWebApp/src/main/java/it/academy/TestWebApp.java
 create mode 100644 Task_9/readmy.txt
 create mode 100644 Task_9/testWebApp/pom.xml
 create mode 100644 Task_9/testWebApp/src/main/java/it/academy/TestWebApp.java

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work (master)
$ git log
commit 359ca9ee6d928e795cbd3ff040ba4c2e96a4d925 (HEAD -> master)
commit 359ca9ee6d928e795cbd3ff040ba4c2e96a4d925 (HEAD -> master)
Author: lu55y <mu11er100500@gmail.com>
Date:   Sat Dec 26 03:36:18 2020 +0300

    taks_9


mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13 (master)
$ git branch task10-13

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13 (master)
$ git checkout task10-13
Switched to branch 'task10-13'

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13 (task10-13)
$ cd testWebApp/

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp (task10-13)
$ cd src/

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src (task10-13)
$ cd main/

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main (task10-13)
$ mkdir resources

mkdir: cannot create directory ‘resources’: File exists

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main (task10-13)
$ dir
java  resources

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main (task10-13)
$ cd resources/

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (task10-13)
$ dir
10-13.txt

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (task10-13)
$ vim 10-13.txt

"its my home work"

esc + :wq

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp (task10-13)
$ git add src/main/resources/10-13.txt
warning: LF will be replaced by CRLF in Task_10-13/testWebApp/src/main/resources/10-13.txt.
The file will have its original line endings in your working directory

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp (task10-13)
$ git commit -a -m "1st changes"
[task10-13 5f0c9bf] 1st changes
 1 file changed, 1 insertion(+)
 create mode 100644 Task_10-13/testWebApp/src/main/resources/10-13.txt

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (task10-13)
$ vim 10-13.txt


"its my home work
do next changes"

esc + :wq


mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (task10-13)
$ git status
On branch task10-13
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   10-13.txt

no changes added to commit (use "git add" and/or "git commit -a")

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (task10-13)
$ git add 10-13.txt
warning: LF will be replaced by CRLF in Task_10-13/testWebApp/src/main/resources/10-13.txt.
The file will have its original line endings in your working directory

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (task10-13)
$ git commit -a -m "second changes"
[task10-13 be18867] second changes
 1 file changed, 1 insertion(+)

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (task10-13)
$ git checkout master
Switched to branch 'master'
Your branch is up to date with 'origin/master'.

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ vim 10-13.txt

"for merging"

esc + :wq

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git add 10-13.txt
warning: LF will be replaced by CRLF in Task_10-13/testWebApp/src/main/resources/10-13.txt.
The file will have its original line endings in your working directory

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git commit -a -m "master commit for merging"
[master 8cdc3f4] master commit for merging
 1 file changed, 1 insertion(+)
 create mode 100644 Task_10-13/testWebApp/src/main/resources/10-13.txt

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git merge task10-13
CONFLICT (add/add): Merge conflict in Task_10-13/testWebApp/src/main/resources/10-13.txt
Auto-merging Task_10-13/testWebApp/src/main/resources/10-13.txt
Automatic merge failed; fix conflicts and then commit the result.

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master|MERGING)
$ vim 10-13.txt


"<<<<<<< HEAD
for merging
=======
its my home work
do next changes
>>>>>>> task10-13"


"its my home work
for merging
do next changes"

esc + :wq

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master|MERGING)
$ git add 10-13.txt

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master|MERGING)
$ git commit -a -m "megrging"
[master d0e8c70] megrging

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git push
Enumerating objects: 37, done.
Counting objects: 100% (37/37), done.
Delta compression using up to 4 threads
Compressing objects: 100% (16/16), done.
Writing objects: 100% (32/32), 2.09 KiB | 357.00 KiB/s, done.
Total 32 (delta 5), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (5/5), completed with 1 local object.
To https://github.com/lu55y/jd2_hw.git
   359ca9e..d0e8c70  master -> master

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ vim 10-13.txt

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git add 10-13.txt

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git commit -a -m "task_13"
[master 9dca277] task_13
 1 file changed, 2 insertions(+)

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git reset --help


mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git reset --hard
HEAD is now at 9dca277 task_13

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ vim 10-13.txt

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git reset --hard HEAD
HEAD is now at 9dca277 task_13

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ vim 10-13.txt

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git reset --hard HEAD ~ 3
fatal: C:/Users/mu11er: 'C:/Users/mu11er' is outside repository at 'D:/home-work'

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git reset --hard HEAD ~ 2
fatal: C:/Users/mu11er: 'C:/Users/mu11er' is outside repository at 'D:/home-work'

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git status
On branch master
Your branch is ahead of 'origin/master' by 1 commit.
  (use "git push" to publish your local commits)

nothing to commit, working tree clean

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ vim 10-13.txt

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git add 10-13.txt

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git commit -a -m "task_13_change 1"
[master 73252a1] task_13_change 1
 1 file changed, 1 insertion(+), 1 deletion(-)

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git push
Enumerating objects: 23, done.
Counting objects: 100% (23/23), done.
Delta compression using up to 4 threads
Compressing objects: 100% (10/10), done.
Writing objects: 100% (16/16), 1.09 KiB | 223.00 KiB/s, done.
Total 16 (delta 3), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (3/3), completed with 1 local object.
To https://github.com/lu55y/jd2_hw.git
   d0e8c70..73252a1  master -> master

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git reset --help

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git revert HEAD --no-edit
[master 44a5b25] Revert "task_13_change 1"
 Date: Sat Dec 26 04:57:09 2020 +0300
 1 file changed, 1 insertion(+), 1 deletion(-)

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git status
On branch master
Your branch is ahead of 'origin/master' by 1 commit.
  (use "git push" to publish your local commits)

nothing to commit, working tree clean

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git hist --all
git: 'hist' is not a git command. See 'git --help'.

The most similar command is
        bisect

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git hist
git: 'hist' is not a git command. See 'git --help'.

The most similar command is
        bisect

mu11er@DESKTOP-UASF1KL MINGW64 /D/home-work/Task_10-13/testWebApp/src/main/resources (master)
$ git log
commit 44a5b25f15c80e86479d924ee0c88f848f8f44b2 (HEAD -> master)
Author: lu55y <mu11er100500@gmail.com>
Date:   Sat Dec 26 04:57:09 2020 +0300

    Revert "task_13_change 1"

    This reverts commit 73252a141d1dbb6ecba44a024f34d194b4fafb9d.

commit 73252a141d1dbb6ecba44a024f34d194b4fafb9d (origin/master, origin/HEAD)
Author: lu55y <mu11er100500@gmail.com>
Date:   Sat Dec 26 04:55:15 2020 +0300

    task_13_change 1

commit 9dca27749b3058c29d58ebabfef581be06c834fd
Author: lu55y <mu11er100500@gmail.com>
Date:   Sat Dec 26 04:41:49 2020 +0300

    task_13

commit d0e8c70ce132526a2ff2f4d8caf11346736687e1
Merge: 8cdc3f4 be18867
Author: lu55y <mu11er100500@gmail.com>
Date:   Sat Dec 26 04:38:04 2020 +0300

    megrging

commit 8cdc3f4eee68e2ce91c41c9736a780f942bc5995
Author: lu55y <mu11er100500@gmail.com>
Date:   Sat Dec 26 04:30:53 2020 +0300

    master commit for merging

commit be1886716cc923cf5f85b4f32b93528260f996fd (task10-13)
Author: lu55y <mu11er100500@gmail.com>
Date:   Sat Dec 26 04:15:03 2020 +0300
