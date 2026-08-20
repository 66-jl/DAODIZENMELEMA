# 常用命令
## git常用命令
    git init：在当前目录初始化一个新的本地仓库（生成 .git 文件夹）。

    git clone <仓库地址>：将远程仓库完整下载到本地（默认文件夹名为项目名）。

    git clone <仓库地址> <新文件夹名>：下载并重命名本地文件夹。

    git remote add origin <URL>  将本地仓库关联到远程新地址。

    git reset --soft HEAD~1：撤销上一次 commit，但修改保留在暂存区（重新改说明）。

    git status                  # 1. 看看改了哪些文件

    git add .                   # 2. 添加所有改动到暂存区

    git commit -m "feat: 描述"  # 3. 提交到本地仓库

    git pull                    # 4. 拉取远程最新代码（防止冲突）

    git push                    # 5. 推送到远程仓库
    
# 常用注解
# 模糊定义
# 一些工具